package com.mts.service;

import com.mts.model.Seat;
import java.util.List;

public interface SeatService {

    void addSeat(Seat seat);

    Seat getSeatById(int seatId);

    List<Seat> getAllSeats();

    List<Seat> getAvailableSeats(int showId);
}