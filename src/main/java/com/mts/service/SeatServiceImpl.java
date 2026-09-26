package com.mts.service;

import com.mts.dao.SeatDAO;
import com.mts.dao.SeatDAOImpl;
import com.mts.model.Seat;

import java.util.List;

public class SeatServiceImpl implements SeatService {

    private final SeatDAO seatDAO;

    public SeatServiceImpl() {
        this.seatDAO = new SeatDAOImpl();
    }

    @Override
    public List<Seat> getAvailableSeats(int showId) {

        if (showId <= 0) {
            throw new IllegalArgumentException("Invalid show ID");
        }

        return seatDAO.getAvailableSeats(showId);
    }

    @Override
    public Seat getSeatById(int seatId) {

        if (seatId <= 0) {
            throw new IllegalArgumentException("Invalid seat ID");
        }

        return seatDAO.getSeatById(seatId);
    }
}