package com.mts.dao;

import com.mts.model.Theatre;

import java.util.List;

public interface TheatreDAO {

    //add a theatre
    void addTheatre(Theatre theatre);

    //get theatre by an id
    Theatre getTheatreById(int theatreId);

    //get all theatres list
    List<Theatre> getAllTheatres();
}