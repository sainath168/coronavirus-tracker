package com.project.coronavirustracker.dao;

import com.project.coronavirustracker.model.Tracker;

import java.util.List;

public interface ITrackerDao {
    List<Tracker> getResult();
}
