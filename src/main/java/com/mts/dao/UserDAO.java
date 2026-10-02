package com.mts.dao;

import com.mts.model.User;

import java.util.List;

public interface UserDAO {

    //void is return type and it returns nothing

    //creates the user
    void registerUser(User user);

    //updates the user
    void updateUser(User user);

    //deletes the user
    void deleteUser(int userId);

    //User is return type and returns user object

    //user login
    User login(String email,String password);

    //read operations
    User getUserById(int userId);

    List<User> getAllUsers();


}
