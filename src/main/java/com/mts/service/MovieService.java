package com.mts.service;

import com.mts.model.Movie;
import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    void addMovie(Movie movie);

    Movie getMovieById(int movieId);
}