package com.mts.controller;

import com.mts.model.Seat;
import com.mts.service.SeatService;
import com.mts.service.SeatServiceImpl;

import java.util.List;

public class SeatController {

    private final SeatService seatService;

    public SeatController() {
        this.seatService = new SeatServiceImpl();
    }

    public void addSeat(Seat seat) {
        seatService.addSeat(seat);
    }

    public Seat getSeatById(int seatId) {
        return seatService.getSeatById(seatId);
    }

    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    public List<Seat> getAvailableSeats(int showId) {
        return seatService.getAvailableSeats(showId);
    }
}