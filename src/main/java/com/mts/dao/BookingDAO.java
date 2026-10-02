package com.mts.dao;

import com.mts.model.Booking;

import java.util.List;

public interface BookingDAO {

    Booking addBooking(Booking booking);

    Booking getBookingById(int bookingId);

    List<Booking> getAllBookings();

    void updateBooking(Booking booking);

    void cancelBooking(int bookingId);

    List<Booking> getBookingsByUserId(int userId);
}
