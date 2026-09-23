package com.mts.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//Booking Table fields
public class Booking {
    private int bookingId; //primary key
    private Show show; //represents Show Table relationship
    private User user; //represents User Table relationship
    private LocalDateTime bookingDate;
    private BigDecimal totalAmount;
    private String bookingStatus;

    //no-arg constructor - used to set the values using setters
    public Booking(){

    }

    //parameterized constructor
    public Booking(int bookingId, Show show, User user, LocalDateTime bookingDate, BigDecimal totalAmount, String bookingStatus){
        this.bookingId=bookingId;
        this.show=show;
        this.user=user;
        this.bookingDate=bookingDate;
        this.totalAmount=totalAmount;
        this.bookingStatus=bookingStatus;

    }

    //getters methods - used to access booking data
    public int getBookingId(){
        return bookingId;
    }
    public Show getShow(){
        return show;
    }
    public User getUser(){
        return user;
    }
    public LocalDateTime getBookingDate(){
        return bookingDate;
    }
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }
    public String getBookingStatus(){
        return bookingStatus;
    }

    //setters method - used to modify booking data
    public void setBookingId(int bookingId){
        this.bookingId=bookingId;
    }
    public void setShow(Show show){
        this.show=show;
    }
    public void setUser(User user){
        this.user=user;
    }
    public void setBookingDate(LocalDateTime bookingDate){
        this.bookingDate=bookingDate;
    }
    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount=totalAmount;
    }
    public void setBookingStatus(String bookingStatus){
        this.bookingStatus=bookingStatus;
}
}
