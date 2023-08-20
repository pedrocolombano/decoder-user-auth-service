package com.ead.userauth.service.impl;

import com.ead.commonlib.enumerated.UserStatus;
import com.ead.commonlib.enumerated.UserType;
import com.ead.commonlib.exception.InvalidDataException;
import com.ead.commonlib.exception.ResourceNotFoundException;
import com.ead.userauth.dto.request.PasswordUpdateDTO;
import com.ead.userauth.dto.request.ProfilePictureUpdateDTO;
import com.ead.userauth.dto.request.UserUpdateDTO;
import com.ead.userauth.dto.response.CourseDTO;
import com.ead.userauth.entity.User;
import com.ead.userauth.feignclients.CourseClient;
import com.ead.userauth.repository.UserRepository;
import com.ead.userauth.service.UserService;
import com.ead.userauth.specification.UserSpecificationTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CourseClient courseClient;

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(final UserSpecificationTemplate.UserSpecification specification, final Pageable pageable) {
        return userRepository.findAll(specification, pageable);
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
            log.info("Username {} is already taken.", username);
            throw new InvalidDataException("The username is already being used.");
        }
    }

    private void validateIfEmailIsAlreadyTaken(final String email) {
        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            log.info("Email {} is already taken.", email);
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
        final User user = this.findById(userId);
        user.setImageUrl(profilePictureUpdateDto.getImageUrl());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User subscribeInstructor(final UUID userId) {
        final User instructor = this.findById(userId);
        instructor.setUserType(UserType.INSTRUCTOR);
        return userRepository.save(instructor);
    }

    @Override
    public Page<CourseDTO> findAllCoursesByUser(final UUID userId, final Pageable pageable) {
        return courseClient.getAllCoursesByUserId(userId, pageable);
    }

}
