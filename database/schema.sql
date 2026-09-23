DROP DATABASE IF EXISTS movie_ticket_system;

CREATE DATABASE movie_ticket_system;

USE movie_ticket_system;

CREATE TABLE users (
        user_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(150) NOT NULL UNIQUE,
        phone VARCHAR(20),
        password VARCHAR(255) NOT NULL,
        role VARCHAR(20) NOT NULL
);

CREATE TABLE movies (
        movie_id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(150) NOT NULL,
        language VARCHAR(50),
        genre VARCHAR(50),
        duration INT,
        release_date DATE
);

CREATE TABLE theatres (
        theatre_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        city VARCHAR(100),
        address VARCHAR(255),
        total_seats INT
);

CREATE TABLE seats (
        seat_id INT AUTO_INCREMENT PRIMARY KEY,
        theatre_id INT NOT NULL,
        seat_number VARCHAR(20) NOT NULL,
        seat_type VARCHAR(50),
        price DECIMAL(10,2),

        FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id)
);
CREATE TABLE shows (
       show_id INT AUTO_INCREMENT PRIMARY KEY,
       theatre_id INT NOT NULL,
       movie_id INT NOT NULL,
       show_date DATE NOT NULL,
       start_time TIME NOT NULL,
       end_time TIME NOT NULL,

       FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id),

       FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

CREATE TABLE bookings (
        booking_id INT AUTO_INCREMENT PRIMARY KEY,
        show_id INT NOT NULL,
        user_id INT NOT NULL,
        booking_date DATETIME,
        total_amount DECIMAL(10,2),
        booking_status VARCHAR(50),

        FOREIGN KEY (show_id) REFERENCES shows(show_id),

        FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE booked_seats (
        booked_seat_id INT AUTO_INCREMENT PRIMARY KEY,
        seat_id INT NOT NULL,
        booking_id INT NOT NULL,

        FOREIGN KEY (seat_id) REFERENCES seats(seat_id),

        FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

CREATE TABLE payments (
        payment_id INT AUTO_INCREMENT PRIMARY KEY,
        booking_id INT NOT NULL UNIQUE,
        amount DECIMAL(10,2),
        payment_method VARCHAR(50),
        payment_status VARCHAR(50),
        payment_date DATETIME,

        FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);
