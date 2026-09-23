package com.mts.model;

import java.time.LocalDate;

public class Movie {

    //Movie Table fields
    private int movieId; //primary key
    private String title;
    private String language;
    private String genre;
    private int duration;
    private LocalDate releaseDate;

    //no-arg constructor - used to set the values using setters
    public Movie(){

    }

    //parameterized constructor
    public Movie(int movieId, String title, String language,String genre, int duration, LocalDate releaseDate){
        this.movieId=movieId;
        this.title=title;
        this.language=language;
        this.genre=genre;
        this.duration=duration;
        this.releaseDate=releaseDate;

    }

    //getters methods - used to access movie data
    public int getMovieId(){
        return movieId;
    }
    public String getTitle(){
        return title;
    }
    public String getLanguage(){
        return language;
    }
    public String getGenre(){
        return genre;
    }
    public int getDuration(){
        return duration;
    }
    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    //setters method - used to modify movie data
    public void setMovieId(int movieId){
        this.movieId=movieId;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setLanguage(String language){
        this.language=language;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public void setDuration(int duration){
        this.duration=duration;
    }
    public void setReleaseDate(LocalDate releaseDate){
        this.releaseDate=releaseDate;
    }
}
