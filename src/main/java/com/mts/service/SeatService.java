package com.mts.service;

import com.mts.model.Seat;
import java.util.List;

public interface SeatService {

    List<Seat> getAvailableSeats(int showId);

    Seat getSeatById(int seatId);
}