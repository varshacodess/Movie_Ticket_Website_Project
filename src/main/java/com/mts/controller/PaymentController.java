package com.mts.controller;

import com.mts.model.Payment;
import com.mts.service.PaymentService;
import com.mts.service.PaymentServiceImpl;

public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController() {
        this.paymentService = new PaymentServiceImpl();
    }

    public void makePayment(Payment payment) {
        paymentService.makePayment(payment);
    }

    public Payment getPaymentById(int paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    public void updatePayment(Payment payment) {
        paymentService.updatePayment(payment);
    }
}