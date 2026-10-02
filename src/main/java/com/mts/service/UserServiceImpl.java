package com.mts.service;

import com.mts.dao.UserDAO;
import com.mts.dao.UserDAOImpl;
import com.mts.model.User;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    // Constructor for Mockito testing
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void registerUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (isInvalid(user.getName())) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (isInvalid(user.getEmail())) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (isInvalid(user.getPhone())) {
            throw new IllegalArgumentException("Phone cannot be empty");
        }

        if (isInvalid(user.getPassword())) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        userDAO.registerUser(user);
    }

    @Override
    public User login(String email, String password) {

        if (isInvalid(email)) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (isInvalid(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        return userDAO.login(email, password);
    }

    private boolean isInvalid(String value) {

        return value == null
                || value.trim().isEmpty()
                || value.trim().equalsIgnoreCase("null");
    }
}