package com.ead.userauth.service.impl;

import com.ead.userauth.dto.param.UserFiltersDTO;
import com.ead.userauth.dto.request.PasswordUpdateDTO;
import com.ead.userauth.dto.request.ProfilePictureUpdateDTO;
import com.ead.userauth.dto.request.UserUpdateDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.entity.enumerated.UserStatus;
import com.ead.userauth.entity.enumerated.UserType;
import com.ead.userauth.exception.InvalidDataException;
import com.ead.userauth.exception.ResourceNotFoundException;
import com.ead.userauth.repository.UserRepository;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(final Pageable pageable, UserFiltersDTO filters) {
        return userRepository.find(pageable, filters);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(final UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    @Transactional
    public void deleteById(final UUID userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found.");
        }
    }

    @Override
    @Transactional
    public User insertUser(final User user) {
        validateUserData(user);
        setUserDefaultData(user);
        return userRepository.save(user);
    }

    private void validateUserData(final User user) {
        validateIfEmailIsAlreadyTaken(user.getEmail());
        validateIfUsernameIsAlreadyTaken(user.getUsername());
    }

    private void validateIfUsernameIsAlreadyTaken(final String username) {
        boolean exists = userRepository.existsByUsername(username);
        if (exists) {
            throw new InvalidDataException("The username is already being used.");
        }
    }

    private void validateIfEmailIsAlreadyTaken(final String email) {
        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            throw new InvalidDataException("The email is already being used.");
        }
    }

    private void setUserDefaultData(final User user) {
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.STUDENT);
    }

    @Override
    @Transactional
    public User updateUser(final UUID userId, final UserUpdateDTO userDto) {
        User user = this.findById(userId);
        updateUserData(user, userDto);
        return userRepository.save(user);
    }

    private void updateUserData(final User user, final UserUpdateDTO userUpdateDTO) {
        user.setFullName(userUpdateDTO.getFullName());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setDocument(userUpdateDTO.getDocument());
    }

    @Override
    @Transactional
    public void updateUserPassword(final UUID userId, final PasswordUpdateDTO passwordUpdateDto) {
        User user = this.findById(userId);
        validateIfUserPasswordIsCorrect(user, passwordUpdateDto.getCurrentPassword());
        user.setPassword(passwordUpdateDto.getNewPassword());
        userRepository.save(user);
    }

    private void validateIfUserPasswordIsCorrect(final User user, final String currentPassword) {
        if (!user.getPassword().equals(currentPassword)) {
            throw new InvalidDataException("User not found or password is incorrect.");
        }
    }

    @Override
    @Transactional
    public void updateUserProfilePicture(final UUID userId, final ProfilePictureUpdateDTO profilePictureUpdateDto) {
        User user = this.findById(userId);
        user.setImageUrl(profilePictureUpdateDto.getImageUrl());
        userRepository.save(user);
    }

}
