package com.mts.controller;

import com.mts.model.Show;
import com.mts.service.ShowService;
import com.mts.service.ShowServiceImpl;

import java.util.List;

public class ShowController {

    private final ShowService showService;

    public ShowController() {
        this.showService = new ShowServiceImpl();
    }

    public void addShow(Show show) {
        showService.addShow(show);
    }

    public Show getShowById(int showId) {
        return showService.getShowById(showId);
    }

    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    public void updateShow(Show show) {
        showService.updateShow(show);
    }

    public void deleteShow(int showId) {
        showService.deleteShow(showId);
    }

    public List<Show> getShowsByMovie(int movieId) {
        return showService.getShowsByMovie(movieId);
    }
}