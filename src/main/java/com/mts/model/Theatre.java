package com.mts.model;

public class Theatre {

    //Theatre Table fields
    private int theatreId; //primary key
    private String name;
    private String city;
    private String address;
    private int totalSeats;

    //no-arg constructor - used to set the values using setters
    public Theatre(){

    }

    //parameterized constructor
    public Theatre(int theatreId, String name, String city, String address, int totalSeats){
        this.theatreId=theatreId;
        this.name=name;
        this.city=city;
        this.address=address;
        this.totalSeats=totalSeats;
    }

    //getters methods - used to access theatre data
    public int getTheatreId(){
        return theatreId;
    }
    public String getName(){
        return name;
    }
    public String getCity(){
        return city;
    }
    public String getAddress(){
        return address;
    }
    public int getTotalSeats(){
        return totalSeats;
    }

    //setters method - used to modify theatre data
    public void setTheatreId(int theatreId){
        this.theatreId=theatreId;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCity(String city){
        this.city=city;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setTotalSeats(int totalSeats){
        this.totalSeats=totalSeats;
    }
}

