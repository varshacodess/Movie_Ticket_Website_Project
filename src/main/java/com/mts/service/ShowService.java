package com.mts.service;

import com.mts.model.Show;

import java.util.List;

public interface ShowService {

    void addShow(Show show);

    Show getShowById(int showId);

    List<Show> getAllShows();

    void updateShow(Show show);

    void deleteShow(int showId);

    List<Show> getShowsByMovie(int movieId);
}