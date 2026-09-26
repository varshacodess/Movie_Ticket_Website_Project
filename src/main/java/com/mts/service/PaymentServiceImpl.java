package com.mts.service;

import com.mts.dao.PaymentDAO;
import com.mts.dao.PaymentDAOImpl;
import com.mts.model.Payment;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentServiceImpl() {
        this.paymentDAO = new PaymentDAOImpl();
    }

    @Override
    public void makePayment(Payment payment) {

        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment cannot be null");
        }

        if (payment.getBooking() == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null");
        }

        if (payment.getAmount() == null ||
                payment.getAmount().signum() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid payment amount");
        }

        if (payment.getPaymentMethod() == null ||
                payment.getPaymentMethod().isEmpty()) {
            throw new IllegalArgumentException(
                    "Payment method cannot be empty");
        }

        paymentDAO.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(int paymentId) {

        if (paymentId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid payment ID");
        }

        return paymentDAO.getPaymentById(paymentId);
    }

    @Override
    public void updatePayment(Payment payment) {

        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment cannot be null");
        }

        if (payment.getPaymentId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid payment ID");
        }

        paymentDAO.updatePayment(payment);
    }
}