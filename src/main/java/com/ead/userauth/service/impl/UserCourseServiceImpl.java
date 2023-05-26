package com.ead.userauth.service.impl;

import com.ead.userauth.dto.response.CourseDTO;
import com.ead.userauth.proxy.CourseProxy;
import com.ead.userauth.repository.UserCourseRepository;
import com.ead.userauth.service.UserCourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserCourseServiceImpl implements UserCourseService {

    private final CourseProxy courseProxy;

    private final UserCourseRepository userCourseRepository;

    @Override
    public Page<CourseDTO> getAllCoursesByUser(final UUID userId, final Pageable pageable) {
        return courseProxy.getAllByUser(userId, pageable);
    }
}
