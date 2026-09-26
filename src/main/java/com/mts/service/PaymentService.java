package com.mts.service;

import com.mts.model.Payment;

public interface PaymentService {

    void makePayment(Payment payment);

    Payment getPaymentById(int paymentId);

    void updatePayment(Payment payment);
}