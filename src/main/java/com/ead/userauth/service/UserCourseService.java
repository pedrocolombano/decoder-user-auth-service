package com.ead.userauth.service;

import com.ead.userauth.dto.response.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserCourseService {

    Page<CourseDTO> getAllCoursesByUser(UUID userId, Pageable pageable);

}
