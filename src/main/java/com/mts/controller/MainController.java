package com.mts.controller;

import com.mts.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mts.model.Movie;
import com.mts.model.Show;
import com.mts.model.Theatre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {

    private static final Logger logger =
            LoggerFactory.getLogger(MainController.class);

    private final UserController userController;
    private final MovieController movieController;
    private final ShowController showController;
    private final SeatController seatController;
    private final BookingController bookingController;
    private final PaymentController paymentController;
    private final TheatreController theatreController;

    private final Scanner scanner;

    // CONSTRUCTOR
    public MainController() {

        userController = new UserController();
        movieController = new MovieController();
        showController = new ShowController();
        seatController = new SeatController();
        bookingController = new BookingController();
        paymentController = new PaymentController();
        theatreController = new TheatreController();

        scanner = new Scanner(System.in);
    }


    // START APPLICATION
    public void start() {

        while (true) {

            System.out.println("\n===== MOVIE TICKET SYSTEM =====");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.println("Enter choice:");

            try {

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        register();
                        break;

                    case 2:
                        login();
                        break;

                    case 3:
                        System.out.println(
                                "Thank you for using Movie Ticket System."
                        );
                        return;

                    default:
                        System.out.println(
                                "Invalid choice. Please enter 1, 2, or 3."
                        );
                }

            } catch (java.util.InputMismatchException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }


    // REGISTER USER
    private void register() {

        System.out.println("\n===== REGISTER =====");

        try {

            System.out.println("Enter Name:");
            String name = scanner.nextLine();

            System.out.println("Enter Email:");
            String email = scanner.nextLine();

            System.out.println("Enter Phone:");
            String phone = scanner.nextLine();

            System.out.println("Enter Password:");
            String password = scanner.nextLine();

            User user = new User(
                    0,
                    name,
                    email,
                    phone,
                    password,
                    "CUSTOMER"
            );

            userController.registerUser(user);

            System.out.println("Registration successful.");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Registration failed: " + e.getMessage()
            );
        }
    }


    // LOGIN
    private void login() {

        System.out.println("\n===== LOGIN =====");

        try {

            System.out.println("Enter email:");
            String email = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            User user =
                    userController.login(email, password);

            if (user == null) {

                System.out.println(
                        "Invalid email or password."
                );

                return;
            }

            System.out.println(
                    "Login successful. Welcome "
                            + user.getName()
                            + "!"
            );

            if ("ADMIN".equalsIgnoreCase(user.getRole())) {

                adminMenu();

            } else if ("CUSTOMER".equalsIgnoreCase(user.getRole())) {

                customerMenu(user);

            } else {

                System.out.println("Invalid user role.");
            }

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Login failed: " + e.getMessage()
            );
        }
    }


    // =========================
    // ADMIN MENU
    // =========================

    private void adminMenu() {

        while (true) {

            System.out.println("\n===== ADMIN MENU =====");

            System.out.println("1. Movie Management");
            System.out.println("2. Theatre Management");
            System.out.println("3. Seat Management");
            System.out.println("4. Show Management");
            System.out.println("5. Booking Management");
            System.out.println("6. Exit");

            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    movieManagementMenu();
                    break;

                case 2:
                    theatreManagementMenu();
                    break;

                case 3:
                    seatManagementMenu();
                    break;

                case 4:
                    showManagementMenu();
                    break;

                case 5:
                    bookingManagementMenu();
                    break;

                case 6:
                    System.out.println(
                            "Exiting Admin Menu..."
                    );
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // =========================
    // MOVIE MANAGEMENT
    // =========================

    private void movieManagementMenu() {

        while (true) {

            System.out.println("\n===== MOVIE MANAGEMENT =====");

            System.out.println("1. Add Movie");
            System.out.println("2. Get Movie By ID");
            System.out.println("3. View All Movies");
            System.out.println("4. Back");

            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addMovie();
                    break;

                case 2:
                    getMovieById();
                    break;

                case 3:
                    displayMovies();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // ADD MOVIE
    private void addMovie() {

        System.out.println("\n===== ADD MOVIE =====");

        System.out.println("Enter movie title:");
        String title = scanner.nextLine();

        System.out.println("Enter language:");
        String language = scanner.nextLine();

        System.out.println("Enter genre:");
        String genre = scanner.nextLine();

        System.out.println("Enter duration in minutes:");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter release date (YYYY-MM-DD):");

        LocalDate releaseDate =
                LocalDate.parse(scanner.nextLine());

        Movie movie = new Movie(
                0,
                title,
                language,
                genre,
                duration,
                releaseDate
        );

        movieController.addMovie(movie);

        System.out.println("Movie added successfully!");
    }


    // GET MOVIE BY ID
    private void getMovieById() {

        System.out.println("\n===== GET MOVIE BY ID =====");

        System.out.println("Enter Movie ID:");

        int movieId = scanner.nextInt();
        scanner.nextLine();

        Movie movie =
                movieController.getMovieById(movieId);

        if (movie == null) {

            System.out.println("Movie not found.");
            return;
        }

        System.out.println(
                "\nMovie ID: " + movie.getMovieId()
        );

        System.out.println(
                "Title: " + movie.getTitle()
        );

        System.out.println(
                "Language: " + movie.getLanguage()
        );

        System.out.println(
                "Genre: " + movie.getGenre()
        );

        System.out.println(
                "Duration: "
                        + movie.getDuration()
                        + " minutes"
        );

        System.out.println(
                "Release Date: "
                        + movie.getReleaseDate()
        );
    }


    // DISPLAY ALL MOVIES
    private void displayMovies() {

        System.out.println("\n===== ALL MOVIES =====");

        List<Movie> movies =
                movieController.getAllMovies();

        if (movies == null || movies.isEmpty()) {

            System.out.println("No movies available.");
            return;
        }

        for (Movie movie : movies) {

            System.out.println(
                    movie.getMovieId()
                            + " - "
                            + movie.getTitle()
                            + " | "
                            + movie.getLanguage()
                            + " | "
                            + movie.getGenre()
                            + " | "
                            + movie.getDuration()
                            + " mins"
                            + " | Release: "
                            + movie.getReleaseDate()
            );
        }
    }


    // =========================
    // THEATRE MANAGEMENT
    // =========================

    private void theatreManagementMenu() {

        while (true) {

            System.out.println(
                    "\n===== THEATRE MANAGEMENT ====="
            );

            System.out.println("1. Add Theatre");
            System.out.println("2. Get Theatre By ID");
            System.out.println("3. View All Theatres");
            System.out.println("4. Back");

            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addTheatre();
                    break;

                case 2:
                    getTheatreById();
                    break;

                case 3:
                    displayTheatres();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // ADD THEATRE
    private void addTheatre() {

        System.out.println("\n===== ADD THEATRE =====");

        System.out.println("Enter theatre name:");
        String name = scanner.nextLine();

        System.out.println("Enter city:");
        String city = scanner.nextLine();

        System.out.println("Enter address:");
        String address = scanner.nextLine();

        System.out.println("Enter total seats:");
        int totalSeats = scanner.nextInt();
        scanner.nextLine();

        Theatre theatre = new Theatre(
                0,
                name,
                city,
                address,
                totalSeats
        );

        theatreController.addTheatre(theatre);

        System.out.println(
                "Theatre added successfully!"
        );
    }


    // DISPLAY ALL THEATRES
    private void displayTheatres() {

        System.out.println("\n===== ALL THEATRES =====");

        List<Theatre> theatres =
                theatreController.getAllTheatres();

        if (theatres == null || theatres.isEmpty()) {

            System.out.println("No theatres available.");
            return;
        }

        for (Theatre theatre : theatres) {

            System.out.println(
                    theatre.getTheatreId()
                            + " - "
                            + theatre.getName()
                            + " | "
                            + theatre.getCity()
                            + " | "
                            + theatre.getAddress()
                            + " | Seats: "
                            + theatre.getTotalSeats()
            );
        }
    }


    // GET THEATRE BY ID
    private void getTheatreById() {

        System.out.println(
                "\n===== GET THEATRE BY ID ====="
        );

        System.out.println("Enter Theatre ID:");

        int theatreId = scanner.nextInt();
        scanner.nextLine();

        Theatre theatre =
                theatreController.getTheatreById(theatreId);

        if (theatre == null) {

            System.out.println("Theatre not found.");
            return;
        }

        System.out.println(
                "Theatre ID: "
                        + theatre.getTheatreId()
        );

        System.out.println(
                "Name: "
                        + theatre.getName()
        );

        System.out.println(
                "City: "
                        + theatre.getCity()
        );

        System.out.println(
                "Address: "
                        + theatre.getAddress()
        );

        System.out.println(
                "Total Seats: "
                        + theatre.getTotalSeats()
        );
    }


    // =========================
    // SEAT MANAGEMENT
    // =========================

    private void seatManagementMenu() {

        while (true) {

            System.out.println(
                    "\n===== SEAT MANAGEMENT ====="
            );

            System.out.println("1. Add Seat");
            System.out.println("2. Get Seat By ID");
            System.out.println("3. View All Seats");
            System.out.println("4. Back");

            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addSeat();
                    break;

                case 2:
                    getSeatById();
                    break;

                case 3:
                    displaySeats();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // ADD SEAT
    private void addSeat() {

        System.out.println("\n===== ADD SEAT =====");

        System.out.println("Enter Theatre ID:");

        int theatreId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Seat Number:");

        String seatNumber = scanner.nextLine();

        System.out.println("Enter Seat Type:");

        String seatType = scanner.nextLine();

        System.out.println("Enter Seat Price:");

        double price = scanner.nextDouble();
        scanner.nextLine();

        Theatre theatre = new Theatre();

        theatre.setTheatreId(theatreId);

        Seat seat = new Seat(
                0,
                theatre,
                seatNumber,
                seatType,
                BigDecimal.valueOf(price)
        );

        seatController.addSeat(seat);

        System.out.println(
                "Seat added successfully."
        );
    }


    // DISPLAY ALL SEATS
    private void displaySeats() {

        System.out.println("\n===== ALL SEATS =====");

        List<Seat> seats =
                seatController.getAllSeats();

        if (seats == null || seats.isEmpty()) {

            System.out.println("No seats found.");
            return;
        }

        for (Seat seat : seats) {

            System.out.println(
                    seat.getSeatId()
                            + " - Theatre ID: "
                            + seat.getTheatre().getTheatreId()
                            + " | Seat: "
                            + seat.getSeatNumber()
                            + " | Type: "
                            + seat.getSeatType()
                            + " | Price: Rs. "
                            + seat.getPrice()
            );
        }
    }


    // GET SEAT BY ID
    private void getSeatById() {

        System.out.println(
                "\n===== GET SEAT BY ID ====="
        );

        System.out.println("Enter Seat ID:");

        int seatId = scanner.nextInt();
        scanner.nextLine();

        Seat seat =
                seatController.getSeatById(seatId);

        if (seat == null) {

            System.out.println("Seat not found.");
            return;
        }

        System.out.println(
                "Seat ID: "
                        + seat.getSeatId()
        );

        System.out.println(
                "Seat Number: "
                        + seat.getSeatNumber()
        );

        System.out.println(
                "Seat Type: "
                        + seat.getSeatType()
        );

        System.out.println(
                "Price: Rs."
                        + seat.getPrice()
        );
    }


    // =========================
    // SHOW MANAGEMENT
    // =========================

    private void showManagementMenu() {

        while (true) {

            System.out.println(
                    "\n===== SHOW MANAGEMENT ====="
            );

            System.out.println("1. Add Show");
            System.out.println("2. Get Show By ID");
            System.out.println("3. View All Shows");
            System.out.println("4. Update Show");
            System.out.println("5. Delete Show");
            System.out.println("6. Back");

            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addShow();
                    break;

                case 2:
                    getShowById();
                    break;

                case 3:
                    displayShows();
                    break;

                case 4:
                    updateShow();
                    break;

                case 5:
                    deleteShow();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // ADD SHOW
    private void addShow() {

        System.out.println("\n===== ADD SHOW =====");

        System.out.println("Enter Theatre ID:");

        int theatreId = scanner.nextInt();

        System.out.println("Enter Movie ID:");

        int movieId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(
                "Enter Show Date (YYYY-MM-DD):"
        );

        LocalDate showDate =
                LocalDate.parse(scanner.nextLine());

        System.out.println(
                "Enter Start Time (HH:MM):"
        );

        LocalTime startTime =
                LocalTime.parse(scanner.nextLine());

        System.out.println(
                "Enter End Time (HH:MM):"
        );

        LocalTime endTime =
                LocalTime.parse(scanner.nextLine());

        Theatre theatre = new Theatre();

        theatre.setTheatreId(theatreId);

        Movie movie = new Movie();

        movie.setMovieId(movieId);

        Show show = new Show(
                0,
                theatre,
                movie,
                showDate,
                startTime,
                endTime
        );

        showController.addShow(show);

        System.out.println(
                "Show added successfully."
        );
    }


    // DISPLAY SHOWS
    private void displayShows() {

        System.out.println("\n===== ALL SHOWS =====");

        List<Show> shows =
                showController.getAllShows();

        if (shows == null || shows.isEmpty()) {

            System.out.println("No shows found.");
            return;
        }

        for (Show show : shows) {

            System.out.println(
                    "Show ID: "
                            + show.getShowId()
                            + " | Theatre ID: "
                            + show.getTheatre().getTheatreId()
                            + " | Movie ID: "
                            + show.getMovie().getMovieId()
                            + " | Date: "
                            + show.getShowDate()
                            + " | "
                            + show.getStartTime()
                            + " - "
                            + show.getEndTime()
            );
        }
    }


    // GET SHOW BY ID
    private void getShowById() {

        System.out.println(
                "\n===== GET SHOW BY ID ====="
        );

        System.out.println("Enter Show ID:");

        int showId = scanner.nextInt();
        scanner.nextLine();

        Show show =
                showController.getShowById(showId);

        if (show == null) {

            System.out.println("Show not found.");
            return;
        }

        System.out.println(
                "Show ID: "
                        + show.getShowId()
        );

        System.out.println(
                "Movie: "
                        + show.getMovie().getTitle()
        );

        System.out.println(
                "Theatre: "
                        + show.getTheatre().getName()
        );

        System.out.println(
                "Date: "
                        + show.getShowDate()
        );

        System.out.println(
                "Start Time: "
                        + show.getStartTime()
        );

        System.out.println(
                "End Time: "
                        + show.getEndTime()
        );
    }


    // UPDATE SHOW
    private void updateShow() {

        System.out.println(
                "\n===== UPDATE SHOW ====="
        );

        System.out.println("Enter Show ID:");

        int showId = scanner.nextInt();

        System.out.println("Enter Theatre ID:");

        int theatreId = scanner.nextInt();

        System.out.println("Enter Movie ID:");

        int movieId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(
                "Enter Show Date (YYYY-MM-DD):"
        );

        LocalDate showDate =
                LocalDate.parse(scanner.nextLine());

        System.out.println(
                "Enter Start Time (HH:MM):"
        );

        LocalTime startTime =
                LocalTime.parse(scanner.nextLine());

        System.out.println(
                "Enter End Time (HH:MM):"
        );

        LocalTime endTime =
                LocalTime.parse(scanner.nextLine());

        Theatre theatre = new Theatre();

        theatre.setTheatreId(theatreId);

        Movie movie = new Movie();

        movie.setMovieId(movieId);

        Show show = new Show(
                showId,
                theatre,
                movie,
                showDate,
                startTime,
                endTime
        );

        showController.updateShow(show);

        System.out.println(
                "Show updated successfully."
        );
    }


    // DELETE SHOW
    private void deleteShow() {

        System.out.println(
                "\n===== DELETE SHOW ====="
        );

        System.out.println("Enter Show ID:");

        int showId = scanner.nextInt();
        scanner.nextLine();

        showController.deleteShow(showId);

        System.out.println(
                "Show deleted successfully."
        );
    }


    // =========================
    // BOOKING MANAGEMENT
    // =========================

    private void bookingManagementMenu() {

        while (true) {

            System.out.println(
                    "\n===== BOOKING MANAGEMENT ====="
            );

            System.out.println("1. View All Bookings");
            System.out.println("2. Get Booking By ID");
            System.out.println("3. Back");

            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    displayAllBookings();
                    break;

                case 2:
                    getBookingById();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // DISPLAY ALL BOOKINGS
    private void displayAllBookings() {

        System.out.println(
                "\n===== ALL BOOKINGS ====="
        );

        List<Booking> bookings =
                bookingController.getAllBookings();

        if (bookings == null || bookings.isEmpty()) {

            System.out.println(
                    "No bookings found."
            );

            return;
        }

        for (Booking booking : bookings) {

            System.out.println(
                    "Booking ID: "
                            + booking.getBookingId()
                            + " | User ID: "
                            + booking.getUser().getUserId()
                            + " | Show ID: "
                            + booking.getShow().getShowId()
                            + " | Date: "
                            + booking.getBookingDate()
                            + " | Amount: Rs."
                            + booking.getTotalAmount()
                            + " | Status: "
                            + booking.getBookingStatus()
            );
        }
    }


    // GET BOOKING BY ID
    private void getBookingById() {

        System.out.println(
                "\n===== GET BOOKING BY ID ====="
        );

        System.out.println("Enter Booking ID:");

        int bookingId = scanner.nextInt();
        scanner.nextLine();

        Booking booking =
                bookingController.getBookingById(
                        bookingId
                );

        if (booking == null) {

            System.out.println(
                    "Booking not found."
            );

            return;
        }

        System.out.println(
                "Booking ID: "
                        + booking.getBookingId()
        );

        System.out.println(
                "Booking Date: "
                        + booking.getBookingDate()
        );

        System.out.println(
                "Total Amount: Rs."
                        + booking.getTotalAmount()
        );

        System.out.println(
                "Booking Status: "
                        + booking.getBookingStatus()
        );
    }


    // =========================
    // CUSTOMER MENU
    // =========================

    private void customerMenu(User user) {

        while (true) {

            System.out.println("\n===== CUSTOMER MENU =====");

            System.out.println("1. View All Movies");
            System.out.println("2. View Shows By Movie Name");
            System.out.println("3. Book Movie Ticket");
            System.out.println("4. View My Bookings");
            System.out.println("5. Exit");

            System.out.println("Enter choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    displayMovies();
                    break;

                case 2:
                    viewShowsByMovie();
                    break;

                case 3:
                    bookMovieTicket(user);
                    break;

                case 4:
                    getCustomerBooking(user);
                    break;

                case 5:
                    System.out.println(
                            "Thank you for using Movie Ticket Booking System!"
                    );
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // VIEW SHOWS BY MOVIE NAME
    private void viewShowsByMovie() {

        System.out.println(
                "\n===== SHOWS BY MOVIE ====="
        );

        System.out.println(
                "Enter movie name:"
        );

        String movieName =
                scanner.nextLine();

        List<Movie> movies =
                movieController.getAllMovies();

        Movie selectedMovie = null;

        for (Movie movie : movies) {

            if (movie.getTitle()
                    .equalsIgnoreCase(
                            movieName.trim()
                    )) {

                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie == null) {

            System.out.println(
                    "Movie not found."
            );

            return;
        }

        logger.info(
                "Searching shows for movie: {}",
                selectedMovie.getTitle()
        );

        List<Show> shows =
                showController.getShowsByMovie(
                        selectedMovie.getMovieId()
                );

        if (shows == null || shows.isEmpty()) {

            System.out.println(
                    "No shows available for this movie."
            );

            return;
        }

        System.out.println(
                "\n===== SHOWS FOR "
                        + selectedMovie.getTitle()
                        + " ====="
        );

        for (Show show : shows) {

            Theatre theatre = show.getTheatre();

            System.out.println(
                    "Show ID: " + show.getShowId()
                            + " | Theatre: " + show.getTheatre().getName()
                            + " | City: " + show.getTheatre().getCity()
                            + " | Date: " + show.getShowDate()
                            + " | Start: " + show.getStartTime()
                            + " | End: " + show.getEndTime()
            );
        }
    }


    // =========================
    // BOOK MOVIE TICKET
    // =========================

    private void bookMovieTicket(User user) {

        displayMovies();

        // SELECT MOVIE BY NAME
        System.out.println(
                "\nEnter movie name:"
        );

        String movieName =
                scanner.nextLine();

        List<Movie> movies =
                movieController.getAllMovies();

        Movie selectedMovie = null;

        for (Movie movie : movies) {

            if (movie.getTitle()
                    .equalsIgnoreCase(
                            movieName.trim()
                    )) {

                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie == null) {

            System.out.println(
                    "Movie not found."
            );

            return;
        }

        logger.info(
                "Movie selected for booking: {}",
                selectedMovie.getTitle()
        );

        int movieId =
                selectedMovie.getMovieId();

        // GET SHOWS FOR SELECTED MOVIE
        List<Show> shows =
                showController.getShowsByMovie(
                        movieId
                );

        if (shows == null || shows.isEmpty()) {

            System.out.println(
                    "No shows available for this movie."
            );

            return;
        }

        System.out.println(
                "\n===== AVAILABLE SHOWS ====="
        );

        for (Show show : shows) {

            System.out.println(
                    show.getShowId()
                            + " - Date: "
                            + show.getShowDate()
                            + " | Start Time: "
                            + show.getStartTime()
                            + " | End Time: "
                            + show.getEndTime()
            );
        }

        // SELECT SHOW
        System.out.println(
                "\nEnter show ID:"
        );

        int showId =
                scanner.nextInt();

        scanner.nextLine();

        Show selectedShow =
                showController.getShowById(
                        showId
                );

        if (selectedShow == null) {

            System.out.println(
                    "Show not found."
            );

            return;
        }

        logger.info(
                "Show selected for booking. Show ID: {}",
                showId
        );

        // GET AVAILABLE SEATS
        List<Seat> availableSeats =
                seatController.getAvailableSeats(
                        showId
                );

        if (availableSeats == null
                || availableSeats.isEmpty()) {

            System.out.println(
                    "No seats available."
            );

            return;
        }

        System.out.println(
                "\n===== AVAILABLE SEATS ====="
        );

        for (Seat seat : availableSeats) {

            System.out.println(
                    "Seat ID: "
                            + seat.getSeatId()
                            + " | Seat: "
                            + seat.getSeatNumber()
                            + " | Type: "
                            + seat.getSeatType()
                            + " | Price: Rs."
                            + seat.getPrice()
            );
        }

        // SELECT NUMBER OF SEATS
        System.out.println(
                "\nHow many seats do you want to book?"
        );

        int seatCount =
                scanner.nextInt();

        scanner.nextLine();

        if (seatCount < 1 || seatCount > 10) {

            System.out.println(
                    "You can select between 1 and 10 seats."
            );

            return;
        }

        List<Integer> seatIds =
                new ArrayList<>();

        // SELECT SEATS
        for (int i = 0;
             i < seatCount;
             i++) {

            System.out.println(
                    "Enter seat ID "
                            + (i + 1)
                            + ":"
            );

            int seatId =
                    scanner.nextInt();

            scanner.nextLine();

            if (!seatIds.contains(seatId)) {

                seatIds.add(seatId);

            } else {

                System.out.println(
                        "Duplicate seat selected."
                );

                return;
            }
        }

        // CREATE BOOKING
        Booking booking =
                bookingController.createBooking(
                        user.getUserId(),
                        showId,
                        seatIds
                );

        if (booking == null) {

            System.out.println(
                    "Booking failed."
            );

            return;
        }

        logger.info(
                "Booking created successfully. Booking ID: {}",
                booking.getBookingId()
        );

        // BOOKING DETAILS
        System.out.println(
                "\n===== BOOKING DETAILS ====="
        );

        System.out.println(
                "Booking ID: "
                        + booking.getBookingId()
        );

        System.out.println(
                "Movie: "
                        + selectedMovie.getTitle()
        );

        System.out.println(
                "Show ID: "
                        + showId
        );

        System.out.println(
                "Total Amount: Rs."
                        + booking.getTotalAmount()
        );

        System.out.println(
                "Booking Status: "
                        + booking.getBookingStatus()
        );

        // PAYMENT
        makePayment(booking);
    }


    // =========================
    // PAYMENT
    // =========================

    private void makePayment(Booking booking) {

        System.out.println(
                "\n===== PAYMENT ====="
        );

        System.out.println("1. UPI");
        System.out.println("2. CARD");
        System.out.println("3. CASH");

        System.out.println(
                "Select payment method:"
        );

        int choice =
                scanner.nextInt();

        scanner.nextLine();

        String paymentMethod;

        switch (choice) {

            case 1:
                paymentMethod = "UPI";
                break;

            case 2:
                paymentMethod = "CARD";
                break;

            case 3:
                paymentMethod = "CASH";
                break;

            default:
                System.out.println(
                        "Invalid payment method."
                );
                return;
        }

        System.out.println(
                "Confirm payment? (Y/N):"
        );

        String confirmation =
                scanner.nextLine();

        if (confirmation.equalsIgnoreCase("N")) {

            System.out.println("Payment cancelled.");

            bookingController.cancelBooking(booking.getBookingId());

            System.out.println("Booking cancelled and seats released.");

            return;
        }

        Payment payment =
                new Payment();

        payment.setBooking(booking);

        payment.setAmount(
                booking.getTotalAmount()
        );

        payment.setPaymentMethod(
                paymentMethod
        );

        payment.setPaymentStatus(
                "SUCCESS"
        );

        payment.setPaymentDate(
                LocalDateTime.now()
        );

        paymentController.makePayment(
                payment
        );

        booking.setBookingStatus(
                "CONFIRMED"
        );

        bookingController.updateBooking(
                booking
        );

        System.out.println(
                "\n================================"
        );

        System.out.println(
                "       BOOKING CONFIRMED"
        );

        System.out.println(
                "================================"
        );

        System.out.println(
                "Booking ID: "
                        + booking.getBookingId()
        );

        System.out.println(
                "Amount Paid: Rs."
                        + booking.getTotalAmount()
        );

        System.out.println(
                "Payment Method: "
                        + paymentMethod
        );

        System.out.println(
                "Payment Status: SUCCESS"
        );

        System.out.println(
                "Booking Status: CONFIRMED"
        );

        System.out.println(
                "================================"
        );
    }


    // =========================
    // CUSTOMER BOOKING
    // =========================

    // CUSTOMER BOOKINGS
    private void getCustomerBooking(User user) {

        System.out.println(
                "\n===== MY BOOKINGS ====="
        );

        List<Booking> bookings =
                bookingController.getBookingsByUserId(
                        user.getUserId()
                );

        if (bookings == null || bookings.isEmpty()) {

            System.out.println(
                    "No bookings found."
            );

            return;
        }

        for (Booking booking : bookings) {

            System.out.println(
                    "\n------------------------------"
            );

            // MOVIE NAME
            String movieName = "Unknown";

            if (booking.getShow() != null
                    && booking.getShow().getMovie() != null) {

                int movieId =
                        booking.getShow()
                                .getMovie()
                                .getMovieId();

                Movie movie =
                        movieController.getMovieById(
                                movieId
                        );

                if (movie != null) {

                    movieName =
                            movie.getTitle();
                }
            }

            System.out.println(
                    "Movie Name: "
                            + movieName
            );

            // SHOW ID
            if (booking.getShow() != null) {

                System.out.println(
                        "Show ID: "
                                + booking.getShow()
                                .getShowId()
                );

            } else {

                System.out.println(
                        "Show ID: Unknown"
                );
            }

            // BOOKING DATE
            System.out.println(
                    "Booking Date: "
                            + booking.getBookingDate()
            );

            // TOTAL AMOUNT
            System.out.println(
                    "Total Amount: Rs."
                            + booking.getTotalAmount()
            );

            // BOOKING STATUS
            System.out.println(
                    "Booking Status: "
                            + booking.getBookingStatus()
            );

            System.out.println(
                    "------------------------------"
            );
        }
    }



    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        MainController mainController =
                new MainController();

        mainController.start();
    }
}