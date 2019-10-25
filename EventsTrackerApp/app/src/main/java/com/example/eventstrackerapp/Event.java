package com.example.eventstrackerapp;

import java.util.ArrayList;

public class Event {
    private String eventID;
    private String title;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private ArrayList<String> hosts;

    public Event(){ }

    public Event(String eventID, String title, String location, String startDate, String endDate, String startTime, String endTime, ArrayList<String> hosts) {
        this.eventID = eventID;
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hosts = hosts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getEventID() { return eventID; }

    public void setEventID(String eventID) { this.eventID = eventID; }

    public ArrayList<String> getHosts() {
        return hosts;
    }

    public void setHosts(ArrayList<String> hosts) {
        this.hosts = hosts;
    }
}
