package com.mts.service;

import com.mts.model.Show;
import java.util.List;

public interface ShowService {

    List<Show> getShowsByMovie(int movieId);

    Show getShowById(int showId);
}