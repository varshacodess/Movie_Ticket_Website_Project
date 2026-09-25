package com.mts.dao;

import com.mts.model.Seat;
import com.mts.model.Theatre;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAOImpl implements SeatDAO {

    // SQL queries
    private static final String addSeatSqlQuery =
            "INSERT INTO seats(theatre_id, seat_number, seat_type, price) VALUES(?,?,?,?)";

    private static final String getSeatByIdSqlQuery =
            "SELECT * FROM seats WHERE seat_id=?";

    private static final String getAllSeatsSqlQuery =
            "SELECT * FROM seats";

    private static final String updateSeatSqlQuery =
            "UPDATE seats SET theatre_id=?, seat_number=?, seat_type=?, price=? WHERE seat_id=?";

    private static final String deleteSeatSqlQuery =
            "DELETE FROM seats WHERE seat_id=?";


    // creates logger for the SeatDAOImpl
    private static final Logger logger =
            LoggerFactory.getLogger(SeatDAOImpl.class);


    // ADD SEAT
    @Override
    public void addSeat(Seat seat) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(addSeatSqlQuery);

            ps.setInt(1, seat.getTheatre().getTheatreId());
            ps.setString(2, seat.getSeatNumber());
            ps.setString(3, seat.getSeatType());
            ps.setBigDecimal(4, seat.getPrice());

            // executes the INSERT query
            ps.executeUpdate();

            logger.info("Seat added successfully!");

        } catch (SQLException e) {

            logger.error("Failed to add seat", e);
        }
    }


    // UPDATE SEAT
    @Override
    public void updateSeat(Seat seat) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(updateSeatSqlQuery);

            ps.setInt(1, seat.getTheatre().getTheatreId());
            ps.setString(2, seat.getSeatNumber());
            ps.setString(3, seat.getSeatType());
            ps.setBigDecimal(4, seat.getPrice());
            ps.setInt(5, seat.getSeatId());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {

                logger.info("Seat updated successfully with ID: {}",
                        seat.getSeatId());

            } else {

                logger.info("No seat found to update with ID: {}",
                        seat.getSeatId());
            }

        } catch (SQLException e) {

            logger.error("Failed to update seat", e);
        }
    }


    // DELETE SEAT
    @Override
    public void deleteSeat(int seatId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(deleteSeatSqlQuery);

            ps.setInt(1, seatId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {

                logger.info("Seat deleted successfully with ID: {}",
                        seatId);

            } else {

                logger.info("No seat found to delete with ID: {}",
                        seatId);
            }

        } catch (SQLException e) {

            logger.error("Failed to delete seat", e);
        }
    }


    // GET SEAT BY ID
    @Override
    public Seat getSeatById(int seatId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getSeatByIdSqlQuery);

            ps.setInt(1, seatId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("seat_id");
                int theatreId = rs.getInt("theatre_id");
                String seatNumber = rs.getString("seat_number");
                String seatType = rs.getString("seat_type");
                BigDecimal price = rs.getBigDecimal("price");

                Theatre theatre = new Theatre();
                theatre.setTheatreId(theatreId);

                Seat seat = new Seat(
                        id,
                        theatre,
                        seatNumber,
                        seatType,
                        price
                );

                logger.info("Seat found successfully with ID: {}",
                        seatId);

                return seat;
            }

            logger.info("No seat found with ID: {}", seatId);

        } catch (SQLException e) {

            logger.error("Failed to get seat", e);
        }

        return null;
    }


    // GET ALL SEATS
    @Override
    public List<Seat> getAllSeats() {

        List<Seat> seats = new ArrayList<>();

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return seats;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getAllSeatsSqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("seat_id");
                int theatreId = rs.getInt("theatre_id");
                String seatNumber = rs.getString("seat_number");
                String seatType = rs.getString("seat_type");
                BigDecimal price = rs.getBigDecimal("price");

                Theatre theatre = new Theatre();
                theatre.setTheatreId(theatreId);

                Seat seat = new Seat(
                        id,
                        theatre,
                        seatNumber,
                        seatType,
                        price
                );

                seats.add(seat);
            }

            logger.info(
                    "All seats retrieved successfully. Total seats: {}",
                    seats.size()
            );

        } catch (SQLException e) {

            logger.error("Failed to get all seats", e);
        }

        return seats;
    }
}