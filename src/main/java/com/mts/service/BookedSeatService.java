package com.mts.service;

import com.mts.model.BookedSeat;

public interface BookedSeatService {

    void addBookedSeat(BookedSeat bookedSeat);

    void deleteBookedSeat(int bookedSeatId);
}