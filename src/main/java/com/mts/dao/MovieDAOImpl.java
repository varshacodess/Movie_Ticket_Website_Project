package com.mts.dao;

import com.mts.model.Movie;
import com.mts.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    // SQL queries
    private static final String addMovieSqlQuery =
            "INSERT INTO movies(title, language, genre, duration, release_date) VALUES(?,?,?,?,?)";

    private static final String getMovieByIdSqlQuery =
            "SELECT * FROM movies WHERE movie_id=?";

    private static final String getAllMoviesSqlQuery =
            "SELECT * FROM movies";

    private static final String updateMovieSqlQuery =
            "UPDATE movies SET title=?, language=?, genre=?, duration=?, release_date=? WHERE movie_id=?";

    private static final String deleteMovieSqlQuery =
            "DELETE FROM movies WHERE movie_id=?";


    // creates logger for the MovieDAOImpl
    private static final Logger logger =
            LoggerFactory.getLogger(MovieDAOImpl.class);


    // ADD MOVIE
    @Override
    public void addMovie(Movie movie) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return;
            }

            PreparedStatement ps =
                    connection.prepareStatement(addMovieSqlQuery);

            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getLanguage());
            ps.setString(3, movie.getGenre());
            ps.setInt(4, movie.getDuration());
            ps.setDate(5, Date.valueOf(movie.getReleaseDate()));

            // executes the INSERT query
            ps.executeUpdate();

            logger.info("Movie added successfully!");

        } catch (SQLException e) {

            logger.error("Failed to add movie", e);
        }
    }



    // GET MOVIE BY ID
    @Override
    public Movie getMovieById(int movieId) {

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getMovieByIdSqlQuery);

            ps.setInt(1, movieId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("movie_id");
                String title = rs.getString("title");
                String language = rs.getString("language");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                Date releaseDate =
                        rs.getDate("release_date");

                Movie movie = new Movie(
                        id,
                        title,
                        language,
                        genre,
                        duration,
                        releaseDate.toLocalDate()
                );

                logger.info("Movie found successfully with ID: {}",
                        movieId);

                return movie;
            }

            logger.info("No movie found with ID: {}", movieId);

        } catch (SQLException e) {

            logger.error("Failed to get movie", e);
        }

        return null;
    }


    // GET ALL MOVIES
    @Override
    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<>();

        try {
            Connection connection = JdbcUtil.getConnection();

            if (connection == null) {
                return movies;
            }

            PreparedStatement ps =
                    connection.prepareStatement(getAllMoviesSqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("movie_id");
                String title = rs.getString("title");
                String language = rs.getString("language");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");

                Date releaseDate =
                        rs.getDate("release_date");

                Movie movie = new Movie(
                        id,
                        title,
                        language,
                        genre,
                        duration,
                        releaseDate.toLocalDate()
                );

                movies.add(movie);
            }

            logger.info(
                    "All movies retrieved successfully. Total movies: {}",
                    movies.size()
            );

        } catch (SQLException e) {

            logger.error("Failed to get all movies", e);
        }

        return movies;
    }
}