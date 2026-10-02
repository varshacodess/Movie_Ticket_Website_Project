package com.mts.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    private static final String URL=
            "jdbc:mysql://localhost:3306/movie_ticket_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER="movie_user";
    private static final String PASSWORD=System.getenv("DB_PASSWORD");


    public static Connection getConnection() {

        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        }
        catch(SQLException e){
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }

    }

// for testing purpose - to see if database is connected successfully or not
//    public static void main(String[] args) {
//        Connection connection = getConnection();
//
//    }
}
