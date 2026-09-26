package com.mts.dao;

import com.mts.model.Seat;

import java.util.List;

public interface SeatDAO {

    //add a seat
    void addSeat(Seat seat);

    //get seat by an id
    Seat getSeatById(int seatId);

    //get all seats list
    List<Seat> getAllSeats();

    List<Seat> getAvailableSeats(int showId);
}