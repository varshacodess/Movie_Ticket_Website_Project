package com.mts.service;

import com.mts.model.Theatre;

import java.util.List;

public interface TheatreService {

    void addTheatre(Theatre theatre);

    Theatre getTheatreById(int theatreId);

    List<Theatre> getAllTheatres();
}