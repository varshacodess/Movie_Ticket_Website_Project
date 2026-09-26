package com.mts.service;

import com.mts.dao.ShowDAO;
import com.mts.dao.ShowDAOImpl;
import com.mts.model.Show;

import java.util.List;

public class ShowServiceImpl implements ShowService {

    private final ShowDAO showDAO;

    public ShowServiceImpl() {
        this.showDAO = new ShowDAOImpl();
    }

    @Override
    public List<Show> getShowsByMovie(int movieId) {

        if (movieId <= 0) {
            throw new IllegalArgumentException("Invalid movie ID");
        }

        return showDAO.getShowsByMovie(movieId);
    }

    @Override
    public Show getShowById(int showId) {

        if (showId <= 0) {
            throw new IllegalArgumentException("Invalid show ID");
        }

        return showDAO.getShowById(showId);
    }
}