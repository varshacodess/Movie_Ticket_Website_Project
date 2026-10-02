package com.mts.controller;

import com.mts.model.Theatre;
import com.mts.service.TheatreService;
import com.mts.service.TheatreServiceImpl;

import java.util.List;

public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController() {
        this.theatreService = new TheatreServiceImpl();
    }

    // Add theatre
    public void addTheatre(Theatre theatre) {
        theatreService.addTheatre(theatre);
    }

    // Get theatre by ID
    public Theatre getTheatreById(int theatreId) {
        return theatreService.getTheatreById(theatreId);
    }

    // Get all theatres
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }
}