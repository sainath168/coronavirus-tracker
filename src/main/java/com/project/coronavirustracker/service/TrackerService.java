package com.project.coronavirustracker.service;

import com.project.coronavirustracker.dao.ITrackerDao;
import com.project.coronavirustracker.model.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackerService {

    @Autowired
    ITrackerDao iTrackerDao;

    public List<Tracker> getResult() {
        return iTrackerDao.getResult();
    }
}
