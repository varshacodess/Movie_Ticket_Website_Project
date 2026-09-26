package com.mts.dao;

import com.mts.model.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDAOTest {

    @Test
    public void testRegisterUser(){

        //creating object for User to test the register method
        UserDAO userDAO = new UserDAOImpl();

        User user = new User(
                0,
                "Test User 2",
                "testuser3@gmail.com",
                "1234567890",
                "test654",
                "ADMIN"
        );

        userDAO.registerUser(user);
    }

    @Test
    public void testGetUserById(){
        UserDAO userDAO = new UserDAOImpl();

        User user = userDAO.getUserById(1);

        assertNotNull(user);
        assertEquals(1,user.getUserId());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
    }


}
