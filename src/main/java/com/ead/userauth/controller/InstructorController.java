package com.ead.userauth.controller;

import com.ead.userauth.dto.response.UserDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.mapper.UserMapper;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/instructors")
@AllArgsConstructor
public class InstructorController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping("/{userId}/subscription")
    public ResponseEntity<UserDTO> subscribeInstructor(@PathVariable UUID userId) {
        final User instructor = userService.subscribeInstructor(userId);
        return ResponseEntity.ok(userMapper.fromEntityToUserDto(instructor));
    }

}
