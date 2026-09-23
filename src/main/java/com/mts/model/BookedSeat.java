package com.mts.model;

public class BookedSeat {


    //BookedSeat Table fields
    private int bookedSeatId; //primary key
    private Seat seat; //represents Seat Table relationship
    private Booking booking; //represents Booking Table relationship

    //no-arg constructor - used to set the values using setters
    public BookedSeat(){

    }

    //parameterized constructor
    public BookedSeat(int bookedSeatId, Seat seat, Booking booking){
        this.bookedSeatId=bookedSeatId;
        this.seat=seat;
        this.booking=booking;
    }

    //getters methods - used to access booked seat data
    public int getBookedSeatId(){
        return bookedSeatId;
    }
    public Seat getSeat(){
        return seat;
    }
    public Booking getBooking(){
        return booking;
    }

    //setters methods - used to modify booked seat data
    public void setBookedSeatId(int bookedSeatId){
        this.bookedSeatId=bookedSeatId;
    }
    public void setSeat(Seat seat){
        this.seat=seat;
    }
    public void setBooking(Booking booking){
        this.booking=booking;
    }

}
