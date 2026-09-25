package com.mts.dao;

import com.mts.model.Seat;

import java.util.List;

public interface SeatDAO {

    //add a seat
    void addSeat(Seat seat);

    //update a seat
    void updateSeat(Seat seat);

    //delete a seat
    void deleteSeat(int seatId);

    //get seat by an id
    Seat getSeatById(int seatId);

    //get all seats list
    List<Seat> getAllSeats();
}