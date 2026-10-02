package com.mts.dao;

import com.mts.model.Show;

import java.util.List;

public interface ShowDAO {

    // Admin operations
    void addShow(Show show);

    Show getShowById(int showId);

    List<Show> getAllShows();

    void updateShow(Show show);

    void deleteShow(int showId);

    // Customer operation
    List<Show> getShowsByMovie(int movieId);
}