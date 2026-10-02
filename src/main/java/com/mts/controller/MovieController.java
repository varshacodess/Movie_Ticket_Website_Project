package com.mts.controller;

import com.mts.model.Movie;
import com.mts.service.MovieService;
import com.mts.service.MovieServiceImpl;

import java.util.List;

public class MovieController {

    private final MovieService movieService;

    public MovieController() {
        this.movieService = new MovieServiceImpl();
    }

    // Add a movie
    public void addMovie(Movie movie) {
        movieService.addMovie(movie);
    }

    // Get movie by ID
    public Movie getMovieById(int movieId) {
        return movieService.getMovieById(movieId);
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}