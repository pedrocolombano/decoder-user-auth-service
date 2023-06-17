package com.ead.userauth.controller;

import com.ead.userauth.dto.request.UserCourseDTO;
import com.ead.userauth.dto.response.CourseDTO;
import com.ead.userauth.dto.response.SubscribedUserDTO;
import com.ead.userauth.entity.UserCourse;
import com.ead.userauth.mapper.UserCourseMapper;
import com.ead.userauth.service.UserCourseService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Log4j2
public class UserCourseController {

    private final UserCourseService userCourseService;
    private final UserCourseMapper userCourseMapper;

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseDTO>> findAllUserCourses(@PathVariable UUID userId, Pageable pageable) {
        return ResponseEntity.ok(userCourseService.getAllCoursesByUser(userId, pageable));
    }

    @PostMapping("/users/{userId}/courses/subscribe")
    public ResponseEntity<SubscribedUserDTO> subscribeUserIntoCourse(@PathVariable UUID userId, @RequestBody @Valid UserCourseDTO userCourseDto) {
        final UserCourse subscribedUser = userCourseService.subscribeUserIntoCourse(userId, userCourseDto);
        final SubscribedUserDTO response = userCourseMapper.fromEntity(subscribedUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/courses/{courseId}")
    public ResponseEntity<Void> deleteUsersSubscribedIntoCourse(@PathVariable UUID courseId) {
        userCourseService.deleteUsersSubscribedIntoCourse(courseId);
        return ResponseEntity.noContent().build();
    }

}
