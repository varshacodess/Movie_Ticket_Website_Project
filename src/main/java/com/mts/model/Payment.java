package com.mts.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    //Payment Table fields
    private int paymentId; //primary key
    private Booking booking; // represents the Booking relationship
    private BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime paymentDate;

    //no-arg constructor - used to set the values using setters
    public Payment(){

    }

    //parameterized constructor
    public Payment(int paymentId, Booking booking, BigDecimal amount, String paymentMethod, String paymentStatus, LocalDateTime paymentDate){
        this.paymentId=paymentId;
        this.booking=booking;
        this.amount=amount;
        this.paymentMethod=paymentMethod;
        this.paymentStatus=paymentStatus;
        this.paymentDate=paymentDate;
    }

    //getters methods - used to access payment data
    public int getPaymentId(){
        return paymentId;
    }
    public Booking getBooking(){
        return booking;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
    public String getPaymentStatus(){
        return paymentStatus;
    }
    public LocalDateTime getPaymentDate(){
        return paymentDate;
    }

    //setters methods - used to modify payment data
    public void setPaymentId(int paymentId){
        this.paymentId=paymentId;
    }
    public void setBooking(Booking booking){
        this.booking=booking;
    }
    public void setAmount(BigDecimal amount){
        this.amount=amount;
    }
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod=paymentMethod;
    }
    public void setPaymentStatus(String paymentStatus){
        this.paymentStatus=paymentStatus;
    }
    public void setPaymentDate(LocalDateTime paymentDate){
        this.paymentDate=paymentDate;
    }


}
