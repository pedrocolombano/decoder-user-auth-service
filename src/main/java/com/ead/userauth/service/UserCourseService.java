package com.ead.userauth.service;

import com.ead.userauth.dto.request.UserCourseDTO;
import com.ead.userauth.dto.response.CourseDTO;
import com.ead.userauth.entity.UserCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserCourseService {

    Page<CourseDTO> getAllCoursesByUser(UUID userId, Pageable pageable);
    UserCourse subscribeUserIntoCourse(UUID userId, UserCourseDTO userCourseDto);

}
