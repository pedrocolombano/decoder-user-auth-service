package com.ead.userauth.service.impl;

import com.ead.userauth.repository.UserCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCourseServiceImpl {

    private final UserCourseRepository userCourseRepository;

}
