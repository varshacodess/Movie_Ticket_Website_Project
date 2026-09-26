package com.mts.dao;

import com.mts.model.Theatre;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheatreDAOImpl implements TheatreDAO {

    // SQL queries
    private static final String addTheatreSqlQuery =
            "INSERT INTO theatres(name, city, address, total_seats) VALUES(?,?,?,?)";

    private static final String getTheatreByIdSqlQuery =
            "SELECT * FROM theatres WHERE theatre_id=?";

    private static final String getAllTheatresSqlQuery =
            "SELECT * FROM theatres";

    private static final String updateTheatreSqlQuery =
            "UPDATE theatres SET name=?, city=?, address=?, total_seats=? WHERE theatre_id=?";

    private static final String deleteTheatreSqlQuery =
            "DELETE FROM theatres WHERE theatre_id=?";


    // creates logger for the TheatreDAOImpl
    private static final Logger logger =
            LoggerFactory.getLogger(TheatreDAOImpl.class);


    // ADD THEATRE
    @Override
    public void addTheatre(Theatre theatre) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(addTheatreSqlQuery);

            ps.setString(1, theatre.getName());
            ps.setString(2, theatre.getCity());
            ps.setString(3, theatre.getAddress());
            ps.setInt(4, theatre.getTotalSeats());

            // executes the INSERT query
            ps.executeUpdate();

            logger.info("Theatre added successfully!");

        } catch (SQLException e) {

            logger.error("Failed to add theatre", e);
        }
    }


    // GET THEATRE BY ID
    @Override
    public Theatre getTheatreById(int theatreId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getTheatreByIdSqlQuery);

            ps.setInt(1, theatreId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("theatre_id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String address = rs.getString("address");
                int totalSeats = rs.getInt("total_seats");

                Theatre theatre = new Theatre(
                        id,
                        name,
                        city,
                        address,
                        totalSeats
                );

                logger.info("Theatre found successfully with ID: {}",
                        theatreId);

                return theatre;
            }

            logger.info("No theatre found with ID: {}", theatreId);

        } catch (SQLException e) {

            logger.error("Failed to get theatre", e);
        }

        return null;
    }


    // GET ALL THEATRES
    @Override
    public List<Theatre> getAllTheatres() {

        List<Theatre> theatres = new ArrayList<>();

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return theatres;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getAllTheatresSqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("theatre_id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String address = rs.getString("address");
                int totalSeats = rs.getInt("total_seats");

                Theatre theatre = new Theatre(
                        id,
                        name,
                        city,
                        address,
                        totalSeats
                );

                theatres.add(theatre);
            }

            logger.info(
                    "All theatres retrieved successfully. Total theatres: {}",
                    theatres.size()
            );

        } catch (SQLException e) {

            logger.error("Failed to get all theatres", e);
        }

        return theatres;
    }
}