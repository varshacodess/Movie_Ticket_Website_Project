package com.mts.dao;

import com.mts.model.Booking;
import com.mts.model.Movie;
import com.mts.model.Show;
import com.mts.model.User;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private static final String getAllBookingsSqlQuery =
            "SELECT * FROM bookings";

    @Override
    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return bookings;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getAllBookingsSqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

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

                bookings.add(booking);
            }

            logger.info(
                    "All bookings retrieved successfully. Total bookings: {}",
                    bookings.size()
            );

        } catch (SQLException e) {

            logger.error("Failed to get all bookings", e);
        }

        return bookings;
    }

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

    @Override
    public void cancelBooking(int bookingId) {

        String sql =
                "UPDATE bookings " +
                        "SET booking_status=? " +
                        "WHERE booking_id=?";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "CANCELLED");
            ps.setInt(2, bookingId);

            ps.executeUpdate();

            logger.info("Booking cancelled successfully. Booking ID: {}",
                    bookingId);

        } catch (SQLException e) {

            logger.error("Error cancelling booking: {}", bookingId, e);

            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Booking> getBookingsByUserId(int userId) {

        List<Booking> bookings = new ArrayList<>();

        String sql =
                "SELECT b.booking_id, b.show_id, b.user_id, " +
                        "b.booking_date, b.total_amount, b.booking_status, " +
                        "s.movie_id " +
                        "FROM bookings b " +
                        "JOIN shows s ON b.show_id = s.show_id " +
                        "WHERE b.user_id = ? " +
                        "ORDER BY b.booking_date DESC";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement ps =
                     connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int bookingId =
                        rs.getInt("booking_id");

                int showId =
                        rs.getInt("show_id");

                int movieId =
                        rs.getInt("movie_id");

                Timestamp bookingDate =
                        rs.getTimestamp("booking_date");

                BigDecimal totalAmount =
                        rs.getBigDecimal("total_amount");

                String bookingStatus =
                        rs.getString("booking_status");


                // Create Movie object
                Movie movie = new Movie();

                movie.setMovieId(movieId);


                // Create Show object
                Show show = new Show();

                show.setShowId(showId);

                show.setMovie(movie);


                // Create User object
                User user = new User();

                user.setUserId(userId);


                // Create Booking object
                Booking booking = new Booking(
                        bookingId,
                        show,
                        user,
                        bookingDate.toLocalDateTime(),
                        totalAmount,
                        bookingStatus
                );

                bookings.add(booking);
            }

            logger.info(
                    "Retrieved {} bookings for User ID: {}",
                    bookings.size(),
                    userId
            );

        } catch (SQLException e) {

            logger.error(
                    "Error retrieving bookings for User ID: {}",
                    userId,
                    e
            );

            throw new RuntimeException(e);
        }

        return bookings;
    }
}