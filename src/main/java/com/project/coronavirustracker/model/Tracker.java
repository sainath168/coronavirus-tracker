package com.project.coronavirustracker.model;

public class Tracker {
    private String country;
    private String state;
    private Integer currentTotal;

    public Tracker(String country, String state, Integer currentTotal) {
        this.country = country;
        this.state = state;
        this.currentTotal = currentTotal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(Integer currentTotal) {
        this.currentTotal = currentTotal;
    }
}
