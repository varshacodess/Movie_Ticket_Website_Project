package com.mts.service;

import com.mts.dao.SeatDAO;
import com.mts.dao.SeatDAOImpl;
import com.mts.exception.SeatNotAvailableException;
import com.mts.model.Seat;

import java.util.List;

public class SeatServiceImpl implements SeatService {

    private final SeatDAO seatDAO;

    public SeatServiceImpl() {
        this.seatDAO = new SeatDAOImpl();
    }

    public SeatServiceImpl(SeatDAO seatDAO) {
        this.seatDAO = seatDAO;
    }

    @Override
    public List<Seat> getAvailableSeats(int showId) {

        if (showId <= 0) {
            throw new SeatNotAvailableException("Invalid show ID");
        }

        return seatDAO.getAvailableSeats(showId);
    }

    @Override
    public void addSeat(Seat seat) {
        if (seat == null) {
            throw new IllegalArgumentException("Seat cannot be null");
        }

        if (seat.getTheatre() == null) {
            throw new IllegalArgumentException("Theatre cannot be null");
        }

        seatDAO.addSeat(seat);
    }

    @Override
    public Seat getSeatById(int seatId) {

        if (seatId <= 0) {
            throw new SeatNotAvailableException("Invalid seat ID");
        }

        return seatDAO.getSeatById(seatId);
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatDAO.getAllSeats();
    }
}