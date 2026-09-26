package com.mts.service;

import com.mts.model.User;

public interface UserService {

    void registerUser(User user);

    User login(String email, String password);
}