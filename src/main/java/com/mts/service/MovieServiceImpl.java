package com.mts.service;

import com.mts.dao.MovieDAO;
import com.mts.dao.MovieDAOImpl;
import com.mts.model.Movie;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private final MovieDAO movieDAO;

    public MovieServiceImpl() {
        this.movieDAO = new MovieDAOImpl();
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }
}