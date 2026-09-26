package com.mts.dao;

import com.mts.model.Show;

import java.util.List;

public interface ShowDAO {

    List<Show> getShowsByMovie(int movieId);

    Show getShowById(int showId);
}
