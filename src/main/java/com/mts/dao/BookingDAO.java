package com.mts.dao;

import com.mts.model.Booking;

public interface BookingDAO {

    Booking addBooking(Booking booking);

    Booking getBookingById(int bookingId);

    void updateBooking(Booking booking);
}
