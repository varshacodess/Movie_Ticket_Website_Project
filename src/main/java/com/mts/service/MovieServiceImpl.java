package com.mts.service;

import com.mts.dao.MovieDAO;
import com.mts.dao.MovieDAOImpl;
import com.mts.model.Movie;
import com.mts.exception.MovieNotFoundException;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private final MovieDAO movieDAO;

    public MovieServiceImpl() {
        this.movieDAO = new MovieDAOImpl();
    }

    public MovieServiceImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    @Override
    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }

        movieDAO.addMovie(movie);
    }

    @Override
    public Movie getMovieById(int movieId) {
        if (movieId <= 0) {
            throw new MovieNotFoundException("Invalid movie ID");
        }

        return movieDAO.getMovieById(movieId);
    }
}