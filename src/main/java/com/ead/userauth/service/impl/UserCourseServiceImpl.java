package com.ead.userauth.service.impl;

import com.ead.userauth.dto.request.UserCourseDTO;
import com.ead.userauth.dto.response.CourseDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.entity.UserCourse;
import com.ead.userauth.exception.InvalidSubscriptionException;
import com.ead.userauth.proxy.CourseProxy;
import com.ead.userauth.repository.UserCourseRepository;
import com.ead.userauth.service.UserCourseService;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class UserCourseServiceImpl implements UserCourseService {

    private final CourseProxy courseProxy;

    private final UserService userService;
    private final UserCourseRepository userCourseRepository;

    @Override
    public Page<CourseDTO> getAllCoursesByUser(final UUID userId, final Pageable pageable) {
        return courseProxy.getAllByUser(userId, pageable);
    }

    @Override
    @Transactional
    public UserCourse subscribeUserIntoCourse(final UUID userId, final UserCourseDTO userCourseDto) {
        log.info("Subscribing user {} into course {}.", userId, userCourseDto.getCourseId());

        final User user = userService.findById(userId);
        validateIfUserIsAlreadySubscribedIntoCourse(user, userCourseDto);

        final UserCourse userToSubscribe = buildUserCourse(user, userCourseDto.getCourseId());
        return userCourseRepository.save(userToSubscribe);
    }

    private void validateIfUserIsAlreadySubscribedIntoCourse(final User user, final UserCourseDTO userCourseDto) {
        boolean isUserAlreadySubscribed = userCourseRepository.existsByUserAndCourseId(user, userCourseDto.getCourseId());
        if (isUserAlreadySubscribed) {
            log.info("The user {} is already subscribed into course {}.", user.getUserId(), userCourseDto.getCourseId());
            throw new InvalidSubscriptionException("The user is already subscribed into the course.");
        }
    }

    private UserCourse buildUserCourse(final User user, final UUID courseId) {
        return UserCourse.builder()
                .user(user)
                .courseId(courseId)
                .build();
    }
}
