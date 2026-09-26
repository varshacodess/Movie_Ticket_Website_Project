package com.mts.dao;

import com.mts.model.Payment;

public interface PaymentDAO {

    void addPayment(Payment payment);

    Payment getPaymentById(int paymentId);

    void updatePayment(Payment payment);
}