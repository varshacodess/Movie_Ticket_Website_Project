package com.mts.dao;

import com.mts.model.BookedSeat;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookedSeatDAOImpl implements BookedSeatDAO {

    private static final String addBookedSeatSqlQuery =
            "INSERT INTO booked_seats(booking_id, show_id, seat_id) VALUES(?,?,?)";

    private static final String deleteBookedSeatSqlQuery =
            "DELETE FROM booked_seats WHERE booked_seat_id=?";

    private static final String deleteBookedSeatsByBookingId = "DELETE FROM booked_seats WHERE booking_id=?";


    private static final Logger logger =
            LoggerFactory.getLogger(BookedSeatDAOImpl.class);


    @Override
    public void addBookedSeat(BookedSeat bookedSeat) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(addBookedSeatSqlQuery);

            ps.setInt(1, bookedSeat.getBooking().getBookingId());
            ps.setInt(2, bookedSeat.getShow().getShowId());
            ps.setInt(3, bookedSeat.getSeat().getSeatId());

            ps.executeUpdate();

            logger.info("Booked seat added successfully!");

        } catch (SQLException e) {
            logger.error("Failed to add booked seat", e);
        }
    }


    @Override
    public void deleteBookedSeat(int bookedSeatId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(
                            deleteBookedSeatSqlQuery
                    );

            ps.setInt(1, bookedSeatId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                logger.info(
                        "Booked seat deleted successfully with ID: {}",
                        bookedSeatId
                );
            } else {
                logger.info(
                        "No booked seat found with ID: {}",
                        bookedSeatId
                );
            }

        } catch (SQLException e) {
            logger.error("Failed to delete booked seat", e);
        }
    }

    @Override
    public void deleteBookedSeatsByBookingId(int bookingId) {
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteBookedSeatsByBookingId)) {

            ps.setInt(1, bookingId);
            ps.executeUpdate();

            logger.info("Booked seats released for Booking ID: {}", bookingId);

        } catch (SQLException e) {
            logger.error("Error releasing seats for Booking ID: {}", bookingId, e);
            throw new RuntimeException(e);
        }
    }
}