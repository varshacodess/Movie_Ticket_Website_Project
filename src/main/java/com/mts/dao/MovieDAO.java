package com.mts.dao;

import com.mts.model.Movie;

import java.util.List;

public interface MovieDAO {

    //add a movie
    void addMovie(Movie movie);

    //get movie by an id
    Movie getMovieById(int movieId);

    //get all movies list
    List<Movie> getAllMovies();
}
