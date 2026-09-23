package com.mts.model;

import java.math.BigDecimal;

public class Seat {

    //Seat Table fields
    private int seatId; //primary key
    private Theatre theatre; //represents the Seat relationship
    private String seatNumber;
    private String seatType;
    private BigDecimal price;

    //no-arg constructor - used to set the values using setters
    public Seat(){

    }

    //parameterized constructor
    public Seat(int seatId, Theatre theatre, String seatNumber, String seatType, BigDecimal price){
        this.seatId=seatId;
        this.theatre=theatre;
        this.seatNumber=seatNumber;
        this.seatType=seatType;
        this.price=price;
    }

    //getters methods - used to access seat data
    public int getSeatId(){
        return seatId;
    }
    public Theatre getTheatre(){
        return theatre;
    }
    public String getSeatNumber(){
        return seatNumber;
    }
    public String getSeatType(){
        return seatType;
    }
    public BigDecimal getPrice(){
        return price;
    }

    //setters method - used to modify seat data
    public void setSeatId(int seatId){
        this.seatId=seatId;
    }
    public void setTheatre(Theatre theatre){
        this.theatre=theatre;
    }
    public void setSeatNumber(String seatNumber){
        this.seatNumber=seatNumber;
    }
    public void setSeatType(String seatType){
        this.seatType=seatType;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }


}
