package com.mts.service;

import com.mts.model.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(
            int userId,
            int showId,
            List<Integer> seatIds
    );

    Booking getBookingById(int bookingId);

    void updateBooking(Booking booking);
}
