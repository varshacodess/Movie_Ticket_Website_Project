package com.mts.controller;

import com.mts.model.Booking;
import com.mts.service.BookingService;
import com.mts.service.BookingServiceImpl;

import java.util.List;

public class BookingController {

    private final BookingService bookingService;

    public BookingController() {
        this.bookingService = new BookingServiceImpl();
    }

    public Booking createBooking(
            int userId,
            int showId,
            List<Integer> seatIds) {

        return bookingService.createBooking(
                userId,
                showId,
                seatIds
        );
    }

    public Booking getBookingById(int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    public void cancelBooking(int bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    public void updateBooking(Booking booking) {
        bookingService.updateBooking(booking);
    }
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }
    public List<Booking> getBookingsByUserId(int userId) {

        return bookingService.getBookingsByUserId(userId);
    }
}