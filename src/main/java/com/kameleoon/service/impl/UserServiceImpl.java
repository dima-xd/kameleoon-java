package com.kameleoon.service.impl;

import com.kameleoon.entity.User;
import com.kameleoon.repository.UserRepository;
import com.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}