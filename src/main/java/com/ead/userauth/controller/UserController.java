package com.ead.userauth.controller;

import com.ead.userauth.dto.request.PasswordUpdateDTO;
import com.ead.userauth.dto.request.ProfilePictureUpdateDTO;
import com.ead.userauth.dto.request.UserUpdateDTO;
import com.ead.userauth.dto.response.UserDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.mapper.UserMapper;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserDTO> users = userService.findAll(pageable)
                .map(userMapper::fromEntityToUserDto);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(userMapper.fromEntityToUserDto(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID userId,
                                              @RequestBody @Valid UserUpdateDTO userDto) {
        User updatedUser = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(userMapper.fromEntityToUserDto(updatedUser));
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> updateUserPassword(@PathVariable UUID userId,
                                                   @RequestBody @Valid PasswordUpdateDTO passwordUpdateDto) {
        userService.updateUserPassword(userId, passwordUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/profile-picture")
    public ResponseEntity<Void> updateUserProfilePicture(@PathVariable UUID userId,
                                                         @RequestBody @Valid ProfilePictureUpdateDTO profilePictureUpdateDto) {
        userService.updateUserProfilePicture(userId, profilePictureUpdateDto);
        return ResponseEntity.noContent().build();
    }

}
