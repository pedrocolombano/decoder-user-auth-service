package com.ead.userauth.service;

import com.ead.userauth.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAll();
    User findById(UUID userId);
    void deleteById(UUID userId);
    User insertUser(User user);

}
