package com.kameleoon.controller;

import com.kameleoon.entity.User;
import com.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/users", produces="application/json")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User newUser) {
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }
}
