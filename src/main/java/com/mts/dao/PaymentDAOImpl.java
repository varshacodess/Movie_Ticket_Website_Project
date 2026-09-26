package com.mts.dao;

import com.mts.model.Payment;
import com.mts.model.Booking;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PaymentDAOImpl implements PaymentDAO {

    private static final String addPaymentSqlQuery =
            "INSERT INTO payments(booking_id, amount, payment_method, payment_status, payment_date) " +
                    "VALUES(?,?,?,?,?)";

    private static final String getPaymentByIdSqlQuery =
            "SELECT * FROM payments WHERE payment_id=?";

    private static final String updatePaymentSqlQuery =
            "UPDATE payments SET booking_id=?, amount=?, payment_method=?, " +
                    "payment_status=?, payment_date=? WHERE payment_id=?";

    private static final Logger logger =
            LoggerFactory.getLogger(PaymentDAOImpl.class);

    @Override
    public void addPayment(Payment payment) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(addPaymentSqlQuery);

            ps.setInt(1, payment.getBooking().getBookingId());
            ps.setBigDecimal(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setString(4, payment.getPaymentStatus());
            ps.setTimestamp(
                    5,
                    Timestamp.valueOf(payment.getPaymentDate())
            );

            ps.executeUpdate();

            logger.info("Payment added successfully!");

        } catch (SQLException e) {
            logger.error("Failed to add payment", e);
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getPaymentByIdSqlQuery);

            ps.setInt(1, paymentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("payment_id");
                int bookingId = rs.getInt("booking_id");
                java.math.BigDecimal amount =
                        rs.getBigDecimal("amount");
                String paymentMethod =
                        rs.getString("payment_method");
                String paymentStatus =
                        rs.getString("payment_status");
                Timestamp paymentDate =
                        rs.getTimestamp("payment_date");

                Booking booking = new Booking();
                booking.setBookingId(bookingId);

                Payment payment = new Payment(
                        id,
                        booking,
                        amount,
                        paymentMethod,
                        paymentStatus,
                        paymentDate.toLocalDateTime()
                );

                logger.info(
                        "Payment found successfully with ID: {}",
                        paymentId
                );

                return payment;
            }

            logger.info(
                    "No payment found with ID: {}",
                    paymentId
            );

        } catch (SQLException e) {
            logger.error("Failed to get payment", e);
        }

        return null;
    }

    @Override
    public void updatePayment(Payment payment) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(updatePaymentSqlQuery);

            ps.setInt(1, payment.getBooking().getBookingId());
            ps.setBigDecimal(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setString(4, payment.getPaymentStatus());
            ps.setTimestamp(
                    5,
                    Timestamp.valueOf(payment.getPaymentDate())
            );
            ps.setInt(6, payment.getPaymentId());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                logger.info(
                        "Payment updated successfully with ID: {}",
                        payment.getPaymentId()
                );
            } else {
                logger.info(
                        "No payment found to update with ID: {}",
                        payment.getPaymentId()
                );
            }

        } catch (SQLException e) {
            logger.error("Failed to update payment", e);
        }
    }
}