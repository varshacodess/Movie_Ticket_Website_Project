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

    private static final String addShowSqlQuery =
            "INSERT INTO shows(theatre_id, movie_id, show_date, start_time, end_time) VALUES(?,?,?,?,?)";

    private static final String getAllShowsSqlQuery =
            "SELECT * FROM shows";

    private static final String updateShowSqlQuery =
            "UPDATE shows SET theatre_id=?, movie_id=?, show_date=?, start_time=?, end_time=? WHERE show_id=?";

    private static final String deleteShowSqlQuery =
            "DELETE FROM shows WHERE show_id=?";

    private static final String getShowByIdSqlQuery =
            "SELECT * FROM shows WHERE show_id=?";

    private static final String getShowsByMovieSqlQuery =
            "SELECT s.*, t.name AS theatre_name, t.city AS theatre_city " +
                    "FROM shows s " +
                    "JOIN theatres t ON s.theatre_id = t.theatre_id " +
                    "WHERE s.movie_id=?";

    private static final Logger logger =
            LoggerFactory.getLogger(ShowDAOImpl.class);

    @Override
    public void addShow(Show show) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(addShowSqlQuery);

            ps.setInt(1, show.getTheatre().getTheatreId());
            ps.setInt(2, show.getMovie().getMovieId());
            ps.setDate(3, Date.valueOf(show.getShowDate()));
            ps.setTime(4, Time.valueOf(show.getStartTime()));
            ps.setTime(5, Time.valueOf(show.getEndTime()));

            ps.executeUpdate();

            logger.info("Show added successfully!");

        } catch (SQLException e) {
            logger.error("Failed to add show", e);
        }
    }

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
                String theatreName = rs.getString("theatre_name");
                String theatreCity = rs.getString("theatre_city");
                int movieIdFromDb = rs.getInt("movie_id");

                Date showDate = rs.getDate("show_date");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");

                Theatre theatre = new Theatre();
                theatre.setTheatreId(theatreId);
                theatre.setName(theatreName);
                theatre.setCity(theatreCity);

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

    @Override
    public List<Show> getAllShows() {

        List<Show> shows = new ArrayList<>();

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return shows;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getAllShowsSqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

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

                shows.add(show);
            }

            logger.info(
                    "All shows retrieved successfully. Total shows: {}",
                    shows.size()
            );

        } catch (SQLException e) {
            logger.error("Failed to get all shows", e);
        }

        return shows;
    }

    @Override
    public void updateShow(Show show) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(updateShowSqlQuery);

            ps.setInt(1, show.getTheatre().getTheatreId());
            ps.setInt(2, show.getMovie().getMovieId());
            ps.setDate(3, Date.valueOf(show.getShowDate()));
            ps.setTime(4, Time.valueOf(show.getStartTime()));
            ps.setTime(5, Time.valueOf(show.getEndTime()));
            ps.setInt(6, show.getShowId());

            ps.executeUpdate();

            logger.info(
                    "Show updated successfully with ID: {}",
                    show.getShowId()
            );

        } catch (SQLException e) {
            logger.error("Failed to update show", e);
        }
    }

    @Override
    public void deleteShow(int showId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(deleteShowSqlQuery);

            ps.setInt(1, showId);

            ps.executeUpdate();

            logger.info(
                    "Show deleted successfully with ID: {}",
                    showId
            );

        } catch (SQLException e) {
            logger.error("Failed to delete show", e);
        }
    }
}