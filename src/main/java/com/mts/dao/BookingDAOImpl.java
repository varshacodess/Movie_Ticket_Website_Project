package com.mts.dao;

import com.mts.model.Booking;
import com.mts.model.Show;
import com.mts.model.User;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

public class BookingDAOImpl implements BookingDAO {

    private static final String addBookingSqlQuery =
            "INSERT INTO bookings(show_id, user_id, booking_date, total_amount, booking_status) " +
                    "VALUES(?,?,?,?,?)";

    private static final String getBookingByIdSqlQuery =
            "SELECT * FROM bookings WHERE booking_id=?";

    private static final String updateBookingSqlQuery =
            "UPDATE bookings SET show_id=?, user_id=?, booking_date=?, " +
                    "total_amount=?, booking_status=? WHERE booking_id=?";

    private static final Logger logger =
            LoggerFactory.getLogger(BookingDAOImpl.class);


    @Override
    public Booking addBooking(Booking booking) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(
                            addBookingSqlQuery,
                            Statement.RETURN_GENERATED_KEYS
                    );

            ps.setInt(1, booking.getShow().getShowId());
            ps.setInt(2, booking.getUser().getUserId());
            ps.setTimestamp(
                    3,
                    Timestamp.valueOf(booking.getBookingDate())
            );
            ps.setBigDecimal(4, booking.getTotalAmount());
            ps.setString(5, booking.getBookingStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                int bookingId = rs.getInt(1);

                booking.setBookingId(bookingId);

                logger.info(
                        "Booking created successfully with ID: {}",
                        bookingId
                );

                return booking;
            }

        } catch (SQLException e) {
            logger.error("Failed to add booking", e);
        }

        return null;
    }


    @Override
    public Booking getBookingById(int bookingId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(
                            getBookingByIdSqlQuery
                    );

            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("booking_id");
                int showId = rs.getInt("show_id");
                int userId = rs.getInt("user_id");

                Timestamp bookingDate =
                        rs.getTimestamp("booking_date");

                BigDecimal totalAmount =
                        rs.getBigDecimal("total_amount");

                String bookingStatus =
                        rs.getString("booking_status");

                Show show = new Show();
                show.setShowId(showId);

                User user = new User();
                user.setUserId(userId);

                Booking booking = new Booking(
                        id,
                        show,
                        user,
                        bookingDate.toLocalDateTime(),
                        totalAmount,
                        bookingStatus
                );

                logger.info(
                        "Booking found successfully with ID: {}",
                        bookingId
                );

                return booking;
            }

            logger.info(
                    "No booking found with ID: {}",
                    bookingId
            );

        } catch (SQLException e) {
            logger.error("Failed to get booking", e);
        }

        return null;
    }


    @Override
    public void updateBooking(Booking booking) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(
                            updateBookingSqlQuery
                    );

            ps.setInt(1, booking.getShow().getShowId());
            ps.setInt(2, booking.getUser().getUserId());
            ps.setTimestamp(
                    3,
                    Timestamp.valueOf(booking.getBookingDate())
            );
            ps.setBigDecimal(4, booking.getTotalAmount());
            ps.setString(5, booking.getBookingStatus());
            ps.setInt(6, booking.getBookingId());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                logger.info(
                        "Booking updated successfully with ID: {}",
                        booking.getBookingId()
                );
            } else {
                logger.info(
                        "No booking found to update with ID: {}",
                        booking.getBookingId()
                );
            }

        } catch (SQLException e) {
            logger.error("Failed to update booking", e);
        }
    }
}