package com.mts.service;

import com.mts.dao.BookedSeatDAO;
import com.mts.dao.BookedSeatDAOImpl;
import com.mts.dao.BookingDAO;
import com.mts.dao.BookingDAOImpl;
import com.mts.exception.BookingException;
import com.mts.exception.SeatNotAvailableException;
import com.mts.model.BookedSeat;
import com.mts.model.Booking;
import com.mts.model.Seat;
import com.mts.model.Show;
import com.mts.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingServiceImpl implements BookingService {

    private final BookingDAO bookingDAO;
    private final BookedSeatDAO bookedSeatDAO;
    private final SeatService seatService;
    private final ShowService showService;

    public BookingServiceImpl() {
        this.bookingDAO = new BookingDAOImpl();
        this.bookedSeatDAO = new BookedSeatDAOImpl();
        this.seatService = new SeatServiceImpl();
        this.showService = new ShowServiceImpl();
    }

    public BookingServiceImpl(
            BookingDAO bookingDAO,
            BookedSeatDAO bookedSeatDAO,
            SeatService seatService,
            ShowService showService) {

        this.bookingDAO = bookingDAO;
        this.bookedSeatDAO = bookedSeatDAO;
        this.seatService = seatService;
        this.showService = showService;
    }

    @Override
    public Booking createBooking(
            int userId,
            int showId,
            List<Integer> seatIds) {

        if (userId <= 0) {
            throw new BookingException("Invalid user ID");
        }

        if (showId <= 0) {
            throw new BookingException("Invalid show ID");
        }

        if (seatIds == null || seatIds.isEmpty()) {
            throw new BookingException(
                    "At least one seat must be selected");
        }

        if (seatIds.size() > 10) {
            throw new BookingException(
                    "Maximum 10 seats can be selected");
        }

        Set<Integer> uniqueSeatIds = new HashSet<>(seatIds);

        if (uniqueSeatIds.size() != seatIds.size()) {
            throw new BookingException(
                    "Duplicate seats cannot be selected");
        }

        Show show = showService.getShowById(showId);

        if (show == null) {
            throw new BookingException("Show not found");
        }

        List<Seat> availableSeats =
                seatService.getAvailableSeats(showId);

        BigDecimal totalAmount =
                getTotalAmount(seatIds, availableSeats);

        Booking booking = new Booking(
                0,
                show,
                createUser(userId),
                LocalDateTime.now(),
                totalAmount,
                "PENDING"
        );

        booking = bookingDAO.addBooking(booking);

        if (booking == null) {
            throw new BookingException(
                    "Failed to create booking");
        }

        for (Integer seatId : seatIds) {

            Seat selectedSeat = null;

            for (Seat seat : availableSeats) {

                if (seat.getSeatId() == seatId) {
                    selectedSeat = seat;
                    break;
                }
            }

            if (selectedSeat == null) {
                throw new SeatNotAvailableException(
                        "Seat " + seatId + " is not available");
            }

            BookedSeat bookedSeat = new BookedSeat(
                    0,
                    selectedSeat,
                    show,
                    booking
            );

            bookedSeatDAO.addBookedSeat(bookedSeat);
        }

        return booking;
    }

    private User createUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    private BigDecimal getTotalAmount(
            List<Integer> seatIds,
            List<Seat> availableSeats) {

        BigDecimal total = BigDecimal.ZERO;

        for (Integer seatId : seatIds) {

            for (Seat seat : availableSeats) {

                if (seat.getSeatId() == seatId) {
                    total = total.add(seat.getPrice());
                    break;
                }
            }
        }

        return total;
    }

    @Override
    public Booking getBookingById(int bookingId) {

        if (bookingId <= 0) {
            throw new BookingException(
                    "Invalid booking ID");
        }

        return bookingDAO.getBookingById(bookingId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    @Override
    public void cancelBooking(int bookingId) {
        Booking booking = bookingDAO.getBookingById(bookingId);

        if (booking == null) {
            throw new BookingException("Booking not found.");
        }

        if ("CANCELLED".equalsIgnoreCase(booking.getBookingStatus())) {
            throw new BookingException("Booking is already cancelled.");
        }

        bookingDAO.cancelBooking(bookingId);

        bookedSeatDAO.deleteBookedSeatsByBookingId(bookingId);
    }

    @Override
    public void updateBooking(Booking booking) {

        if (booking == null) {
            throw new BookingException(
                    "Booking cannot be null");
        }

        if (booking.getBookingId() <= 0) {
            throw new BookingException(
                    "Invalid booking ID");
        }

        bookingDAO.updateBooking(booking);
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {

        return bookingDAO.getBookingsByUserId(userId);
    }
}