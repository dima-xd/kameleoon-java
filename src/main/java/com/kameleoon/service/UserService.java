package com.kameleoon.service;

import com.kameleoon.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User newUser);
    Optional<User> findById(Long userId);
}
