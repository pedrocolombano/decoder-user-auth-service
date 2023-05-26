package com.ead.userauth.controller;

import com.ead.userauth.dto.request.UserInsertDTO;
import com.ead.userauth.dto.response.UserDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.mapper.UserMapper;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Log4j2
public class AuthenticationController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> insertUser(@RequestBody @Valid UserInsertDTO userInsertDto) {
        log.debug("Inserting user: {}", userInsertDto.toString());

        final User createdUser = userService.insertUser(userMapper.fromUserInsertDtoToEntity(userInsertDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(createdUser.getUserId())
                .toUri();

        log.info("User created: {}", createdUser.getUserId());
        return ResponseEntity.created(uri).body(userMapper.fromEntityToUserDto(createdUser));
    }

}
