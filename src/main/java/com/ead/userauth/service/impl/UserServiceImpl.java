package com.ead.userauth.service.impl;

import com.ead.userauth.entity.User;
import com.ead.userauth.entity.enumerated.UserStatus;
import com.ead.userauth.entity.enumerated.UserType;
import com.ead.userauth.exception.InvalidDataException;
import com.ead.userauth.exception.ResourceNotFoundException;
import com.ead.userauth.repository.UserRepository;
import com.ead.userauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
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

    private void setUserDefaultData(User user) {
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.STUDENT);
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
    }
}
