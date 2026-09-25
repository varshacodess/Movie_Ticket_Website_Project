package com.mts.dao;

import com.mts.model.User;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    // SQL queries
    private static final String registerUserSqlQuery =
            "INSERT INTO users(name, email, phone, password, role) VALUES(?,?,?,?,?)";

    private static final String getUserByIdSqlQuery =
            "SELECT * FROM users WHERE user_id=?";

    private static final String getAllUsersSqlQuery =
            "SELECT * FROM users";

    private static final String updateUserSqlQuery =
            "UPDATE users SET name=?, email=?, phone=?, password=?, role=? WHERE user_id=?";

    private static final String deleteUserSqlQuery =
            "DELETE FROM users WHERE user_id=?";

    private static final String loginUserSqlQuery =
            "SELECT * FROM users WHERE email=? AND password=?";


    // creates logger for the UserDAOImpl and gets its name as a string
    private static final Logger logger =
            LoggerFactory.getLogger(UserDAOImpl.class);


    // CREATE USER
    @Override
    public void registerUser(User user) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps = connection.prepareStatement(registerUserSqlQuery);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());

            // executes the INSERT query
            ps.executeUpdate();

            logger.info("User registered successfully!");

        } catch (SQLException e) {

            logger.error("Failed to register user", e);
        }
    }


    // UPDATE USER
    @Override
    public void updateUser(User user) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps = connection.prepareStatement(updateUserSqlQuery);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setInt(6, user.getUserId());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {

                logger.info("User updated successfully with ID: {}",
                        user.getUserId());

            } else {

                logger.info("No user found to update with ID: {}",
                        user.getUserId());
            }

        } catch (SQLException e) {

            logger.error("Failed to update user", e);
        }
    }


    // DELETE USER
    @Override
    public void deleteUser(int userId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps = connection.prepareStatement(deleteUserSqlQuery);

            ps.setInt(1, userId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {

                logger.info("User deleted successfully with ID: {}",
                        userId);

            } else {

                logger.info("No user found to delete with ID: {}",
                        userId);
            }

        } catch (SQLException e) {

            logger.error("Failed to delete user", e);
        }
    }


    // LOGIN USER
    @Override
    public User login(String email, String password) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps = connection.prepareStatement(loginUserSqlQuery);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String userEmail = rs.getString("email");
                String phone = rs.getString("phone");
                String userPassword = rs.getString("password");
                String role = rs.getString("role");

                User user = new User(
                        id,
                        name,
                        userEmail,
                        phone,
                        userPassword,
                        role
                );

                logger.info("User login successful for email: {}",
                        email);

                return user;
            }

            logger.info("Invalid login credentials for email: {}",
                    email);

        } catch (SQLException e) {

            logger.error("Failed to login user", e);
        }

        return null;
    }


    // GET USER BY ID
    @Override
    public User getUserById(int userId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getUserByIdSqlQuery);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String role = rs.getString("role");

                User user = new User(
                        id,
                        name,
                        email,
                        phone,
                        password,
                        role
                );

                logger.info("User found successfully with ID: {}",
                        userId);

                return user;
            }

            logger.info("No user found with ID: {}", userId);

        } catch (SQLException e) {

            logger.error("Failed to get user", e);
        }

        return null;
    }


    // GET ALL USERS
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return users;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getAllUsersSqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String role = rs.getString("role");

                User user = new User(
                        id,
                        name,
                        email,
                        phone,
                        password,
                        role
                );

                users.add(user);
            }

            logger.info("All users retrieved successfully. Total users: {}",
                    users.size());

        } catch (SQLException e) {

            logger.error("Failed to get all users", e);
        }

        return users;
    }
}