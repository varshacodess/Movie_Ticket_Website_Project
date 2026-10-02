package com.mts.service;

import com.mts.dao.TheatreDAO;
import com.mts.dao.TheatreDAOImpl;
import com.mts.exception.TheatreNotFoundException;
import com.mts.model.Theatre;

import java.util.List;

public class TheatreServiceImpl implements TheatreService {

    private final TheatreDAO theatreDAO;

    public TheatreServiceImpl() {
        this.theatreDAO = new TheatreDAOImpl();
    }

    public TheatreServiceImpl(TheatreDAO theatreDAO) {
        this.theatreDAO = theatreDAO;
    }

    @Override
    public void addTheatre(Theatre theatre) {

        if (theatre == null) {
            throw new IllegalArgumentException("Theatre cannot be null");
        }

        theatreDAO.addTheatre(theatre);
    }

    @Override
    public Theatre getTheatreById(int theatreId) {

        if (theatreId <= 0) {
            throw new TheatreNotFoundException("Invalid theatre ID");
        }

        return theatreDAO.getTheatreById(theatreId);
    }

    @Override
    public List<Theatre> getAllTheatres() {

        return theatreDAO.getAllTheatres();
    }
}