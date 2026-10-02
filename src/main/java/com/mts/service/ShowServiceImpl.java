package com.mts.service;

import com.mts.dao.ShowDAO;
import com.mts.dao.ShowDAOImpl;
import com.mts.exception.ShowNotFoundException;
import com.mts.model.Show;

import java.util.List;

public class ShowServiceImpl implements ShowService {

    private final ShowDAO showDAO;

    public ShowServiceImpl() {
        this.showDAO = new ShowDAOImpl();
    }

    // Constructor for Mockito testing
    public ShowServiceImpl(ShowDAO showDAO) {
        this.showDAO = showDAO;
    }

    @Override
    public List<Show> getShowsByMovie(int movieId) {

        if (movieId <= 0) {
            throw new ShowNotFoundException("Invalid movie ID");
        }

        return showDAO.getShowsByMovie(movieId);
    }

    @Override
    public Show getShowById(int showId) {

        if (showId <= 0) {
            throw new ShowNotFoundException("Invalid show ID");
        }

        return showDAO.getShowById(showId);
    }

    @Override
    public void addShow(Show show) {

        if (show == null) {
            throw new IllegalArgumentException("Show cannot be null");
        }

        if (show.getTheatre() == null) {
            throw new IllegalArgumentException("Theatre cannot be null");
        }

        if (show.getMovie() == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }

        showDAO.addShow(show);
    }

    @Override
    public List<Show> getAllShows() {
        return showDAO.getAllShows();
    }

    @Override
    public void updateShow(Show show) {

        if (show == null) {
            throw new IllegalArgumentException("Show cannot be null");
        }

        if (show.getShowId() <= 0) {
            throw new ShowNotFoundException("Invalid show ID");
        }

        showDAO.updateShow(show);
    }

    @Override
    public void deleteShow(int showId) {

        if (showId <= 0) {
            throw new ShowNotFoundException("Invalid show ID");
        }

        showDAO.deleteShow(showId);
    }
}