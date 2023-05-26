package com.ead.userauth.controller;

import com.ead.userauth.dto.param.UserFiltersDTO;
import com.ead.userauth.dto.request.PasswordUpdateDTO;
import com.ead.userauth.dto.request.ProfilePictureUpdateDTO;
import com.ead.userauth.dto.request.UserUpdateDTO;
import com.ead.userauth.dto.response.UserDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.mapper.UserMapper;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
@Log4j2
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                 UserFiltersDTO filters) {
        Page<UserDTO> users = userService.findAll(pageable, filters)
                .map(userMapper::fromEntityToUserDto);
        users.forEach(user -> user.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findById(user.getUserId())).withSelfRel()));

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(userMapper.fromEntityToUserDto(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID userId) {
        log.debug("Deleting user {}", userId);
        userService.deleteById(userId);

        log.info("User {} successfully deleted.", userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID userId,
                                              @RequestBody @Valid UserUpdateDTO userDto) {
        log.debug("Updating user {}. Received data: {}", userId, userDto.toString());
        User updatedUser = userService.updateUser(userId, userDto);

        log.info("User {} updated.", updatedUser.getUserId());
        return ResponseEntity.ok(userMapper.fromEntityToUserDto(updatedUser));
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> updateUserPassword(@PathVariable UUID userId,
                                                   @RequestBody @Valid PasswordUpdateDTO passwordUpdateDto) {
        log.debug("Updating user {} password.", userId);
        userService.updateUserPassword(userId, passwordUpdateDto);

        log.info("User {} successfully updated password.", userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/profile-picture")
    public ResponseEntity<Void> updateUserProfilePicture(@PathVariable UUID userId,
                                                         @RequestBody @Valid ProfilePictureUpdateDTO profilePictureUpdateDto) {
        userService.updateUserProfilePicture(userId, profilePictureUpdateDto);
        return ResponseEntity.noContent().build();
    }

}
