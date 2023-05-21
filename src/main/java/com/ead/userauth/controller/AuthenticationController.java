package com.ead.userauth.controller;

import com.ead.userauth.dto.request.UserInsertDTO;
import com.ead.userauth.dto.response.UserDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.mapper.UserMapper;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserInsertDTO userInsertDto) {
        final User createdUser = userService.insertUser(userMapper.fromUserInsertDtoToEntity(userInsertDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(createdUser.getUserId())
                .toUri();

        return ResponseEntity.created(uri).body(userMapper.fromEntityToUserDto(createdUser));
    }

}
