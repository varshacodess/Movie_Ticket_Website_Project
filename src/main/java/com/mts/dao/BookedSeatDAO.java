package com.mts.dao;

import com.mts.model.BookedSeat;

public interface BookedSeatDAO {

    void addBookedSeat(BookedSeat bookedSeat);

    void deleteBookedSeat(int bookedSeatId);
}