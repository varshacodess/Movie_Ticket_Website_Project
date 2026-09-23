package com.mts.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Show {

    //Show Table Fields
    private int showId; //primary key
    private Theatre theatre; //represents the Theatre relationship
    private Movie movie; //represents the Movie relationship
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;

    //no-arg constructor - used to set the values using setters
    public Show(){

    }

    //parameterized constructor
    public Show(int showId, Theatre theatre, Movie movie, LocalDate showDate, LocalTime startTime, LocalTime endTime){
        this.showId=showId;
        this.theatre=theatre;
        this.movie=movie;
        this.showDate=showDate;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    //getters methods - used to access show data
    public int getShowId(){
        return showId;
    }
    public Theatre getTheatre(){
        return theatre;
    }
    public Movie getMovie(){
        return movie;
    }
    public LocalDate getShowDate(){
        return showDate;
    }
    public LocalTime getStartTime(){
        return startTime;
    }
    public LocalTime getEndTime(){
        return endTime;
    }

    //setters methods - used to modify show data
    public void setShowId(int showId){
        this.showId=showId;
    }
    public void setTheatre(Theatre theatre){
        this.theatre=theatre;
    }
    public void setMovie(Movie movie){
        this.movie=movie;
    }
    public void setShowDate(LocalDate showDate){
        this.showDate=showDate;
    }
    public void setStartTime(LocalTime startTime){
        this.startTime=startTime;
    }
    public void setEndTime(LocalTime endTime){
        this.endTime=endTime;
    }
}
