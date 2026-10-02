package com.mts.controller;

import com.mts.model.User;
import com.mts.service.UserService;
import com.mts.service.UserServiceImpl;

public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    public void registerUser(User user) {
        userService.registerUser(user);
    }

    public User login(String email, String password) {
        return userService.login(email, password);
    }
}