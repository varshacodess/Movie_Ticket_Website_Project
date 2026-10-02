package com.mts.service;

import com.mts.dao.BookedSeatDAO;
import com.mts.dao.BookedSeatDAOImpl;
import com.mts.model.BookedSeat;

public class BookedSeatServiceImpl implements BookedSeatService {

    private final BookedSeatDAO bookedSeatDAO;

    public BookedSeatServiceImpl() {
        this.bookedSeatDAO = new BookedSeatDAOImpl();
    }

    public BookedSeatServiceImpl(BookedSeatDAO bookedSeatDAO) {
        this.bookedSeatDAO = bookedSeatDAO;
    }

    @Override
    public void addBookedSeat(BookedSeat bookedSeat) {

        if (bookedSeat == null) {
            throw new IllegalArgumentException(
                    "Booked seat cannot be null");
        }

        if (bookedSeat.getBooking() == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null");
        }

        if (bookedSeat.getShow() == null) {
            throw new IllegalArgumentException(
                    "Show cannot be null");
        }

        if (bookedSeat.getSeat() == null) {
            throw new IllegalArgumentException(
                    "Seat cannot be null");
        }

        bookedSeatDAO.addBookedSeat(bookedSeat);
    }

    @Override
    public void deleteBookedSeat(int bookedSeatId) {

        if (bookedSeatId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid booked seat ID");
        }

        bookedSeatDAO.deleteBookedSeat(bookedSeatId);
    }
}