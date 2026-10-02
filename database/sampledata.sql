USE movie_ticket_system;

-- =========================================================
-- SAMPLE DATA FOR MOVIE TICKET WEBSITE
-- =========================================================
-- Testing flow:
-- Login -> Movies -> Shows -> Seats -> Booking -> Payment
--
-- Bookings, booked_seats and payments are NOT inserted here.
-- They will be created by the Java application.
-- =========================================================

INSERT INTO users
(name, email, phone, password, role)
VALUES
    ('Varsha', 'varsha@gmail.com', '9999999999', 'varsha@123', 'CUSTOMER'),
    ('Admin', 'admin@gmail.com', '8888888888', 'admin@123', 'ADMIN');

INSERT INTO movies
(title, language, genre, duration, release_date)
VALUES
    ('Interstellar', 'English', 'Science Fiction', 169, '2014-11-07'),
    ('RRR', 'Telugu', 'Action', 182, '2022-03-25'),
    ('The Dark Knight', 'English', 'Action', 152, '2008-07-18'),
    ('Pushpa 2', 'Telugu', 'Action', 200, '2024-12-05');

INSERT INTO theatres
(name, city, address, total_seats)
VALUES
    ('PVR Cinemas', 'Hyderabad', 'Banjara Hills', 8),
    ('INOX', 'Hyderabad', 'Gachibowli', 8);

INSERT INTO seats
(theatre_id, seat_number, seat_type, price)
VALUES
    (1, 'A1', 'REGULAR', 150.00),
    (1, 'A2', 'REGULAR', 150.00),
    (1, 'A3', 'PREMIUM', 200.00),
    (1, 'A4', 'PREMIUM', 200.00),
    (1, 'B1', 'REGULAR', 150.00),
    (1, 'B2', 'REGULAR', 150.00),
    (1, 'B3', 'PREMIUM', 200.00),
    (1, 'B4', 'PREMIUM', 200.00),
    (2, 'A1', 'REGULAR', 120.00),
    (2, 'A2', 'REGULAR', 120.00),
    (2, 'A3', 'PREMIUM', 180.00),
    (2, 'A4', 'PREMIUM', 180.00),
    (2, 'B1', 'REGULAR', 120.00),
    (2, 'B2', 'REGULAR', 120.00),
    (2, 'B3', 'PREMIUM', 180.00),
    (2, 'B4', 'PREMIUM', 180.00);

INSERT INTO shows
(theatre_id, movie_id, show_date, start_time, end_time)
VALUES
    (1, 1, '2026-10-01', '10:00:00', '12:49:00'),
    (1, 2, '2026-10-01', '14:00:00', '17:02:00'),
    (1, 4, '2026-10-01', '18:00:00', '21:20:00'),
    (2, 3, '2026-10-01', '11:00:00', '13:32:00'),
    (2, 2, '2026-10-01', '18:00:00', '21:02:00');

SELECT * FROM users;
SELECT * FROM movies;
SELECT * FROM theatres;
SELECT * FROM seats;
SELECT * FROM shows;
