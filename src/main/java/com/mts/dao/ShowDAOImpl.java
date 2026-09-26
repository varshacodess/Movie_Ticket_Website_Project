package com.mts.dao;

import com.mts.model.Movie;
import com.mts.model.Show;
import com.mts.model.Theatre;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ShowDAOImpl implements ShowDAO {

    private static final String getShowByIdSqlQuery =
            "SELECT * FROM shows WHERE show_id=?";

    private static final String getShowsByMovieSqlQuery =
            "SELECT * FROM shows WHERE movie_id=?";

    private static final Logger logger =
            LoggerFactory.getLogger(ShowDAOImpl.class);

    @Override
    public Show getShowById(int showId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getShowByIdSqlQuery);

            ps.setInt(1, showId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("show_id");
                int theatreId = rs.getInt("theatre_id");
                int movieId = rs.getInt("movie_id");

                Date showDate = rs.getDate("show_date");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");

                Theatre theatre = new Theatre();
                theatre.setTheatreId(theatreId);

                Movie movie = new Movie();
                movie.setMovieId(movieId);

                Show show = new Show(
                        id,
                        theatre,
                        movie,
                        showDate.toLocalDate(),
                        startTime.toLocalTime(),
                        endTime.toLocalTime()
                );

                logger.info(
                        "Show found successfully with ID: {}",
                        showId
                );

                return show;
            }

            logger.info("No show found with ID: {}", showId);

        } catch (SQLException e) {
            logger.error("Failed to get show", e);
        }

        return null;
    }

    @Override
    public List<Show> getShowsByMovie(int movieId) {

        List<Show> shows = new ArrayList<>();

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return shows;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getShowsByMovieSqlQuery);

            ps.setInt(1, movieId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("show_id");
                int theatreId = rs.getInt("theatre_id");
                int movieIdFromDb = rs.getInt("movie_id");

                Date showDate = rs.getDate("show_date");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");

                Theatre theatre = new Theatre();
                theatre.setTheatreId(theatreId);

                Movie movie = new Movie();
                movie.setMovieId(movieIdFromDb);

                Show show = new Show(
                        id,
                        theatre,
                        movie,
                        showDate.toLocalDate(),
                        startTime.toLocalTime(),
                        endTime.toLocalTime()
                );

                shows.add(show);
            }

            logger.info(
                    "Shows retrieved successfully for movie ID: {}. Total shows: {}",
                    movieId,
                    shows.size()
            );

        } catch (SQLException e) {
            logger.error("Failed to get shows by movie", e);
        }

        return shows;
    }
}