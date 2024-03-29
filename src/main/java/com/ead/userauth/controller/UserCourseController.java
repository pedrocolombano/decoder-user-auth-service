package com.ead.userauth.controller;

import com.ead.userauth.dto.response.CourseDTO;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Log4j2
public class UserCourseController {

    private final UserService userService;

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseDTO>> findAllCoursesByUser(@PathVariable UUID userId, Pageable pageable) {
        return ResponseEntity.ok(userService.findAllCoursesByUser(userId, pageable));
    }

}
