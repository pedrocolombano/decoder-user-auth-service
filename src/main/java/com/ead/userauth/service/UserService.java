package com.ead.userauth.service;

import com.ead.userauth.dto.request.PasswordUpdateDTO;
import com.ead.userauth.dto.request.ProfilePictureUpdateDTO;
import com.ead.userauth.dto.request.UserUpdateDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.specification.UserSpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<User> findAll(UserSpecificationTemplate.UserSpecification specification, Pageable pageable);
    User findById(UUID userId);
    void deleteById(UUID userId);
    User insertUser(User user);
    User updateUser(UUID userId, UserUpdateDTO userDto);
    void updateUserPassword(UUID userId, PasswordUpdateDTO passwordUpdateDto);
    void updateUserProfilePicture(UUID userId, ProfilePictureUpdateDTO profilePictureUpdateDto);

}
