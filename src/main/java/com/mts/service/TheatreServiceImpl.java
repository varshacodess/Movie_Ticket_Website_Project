package com.mts.service;

import com.mts.dao.TheatreDAO;
import com.mts.dao.TheatreDAOImpl;
import com.mts.model.Theatre;

public class TheatreServiceImpl implements TheatreService {

    private final TheatreDAO theatreDAO;

    public TheatreServiceImpl() {
        this.theatreDAO = new TheatreDAOImpl();
    }

    @Override
    public Theatre getTheatreById(int theatreId) {

        if (theatreId <= 0) {
            throw new IllegalArgumentException("Invalid theatre ID");
        }

        return theatreDAO.getTheatreById(theatreId);
    }
}