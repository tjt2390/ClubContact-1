package com.example.eventstrackerapp;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String eventID;
    private String title;
    private String location;
    private Date start;
    private Date end;
    private ArrayList<String> hosts;
    private String description;

    public Event(){ }

    public Event(String eventID, String title, String location, Date start, Date end,
                 ArrayList<String> hosts, String description) {
        this.eventID = eventID;
        this.title = title;
        this.location = location;
        this.start = start;
        this.end = end;
        this.hosts = hosts;
        this.description = description;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public Date getStart() { return start; }

    public void setStart(Date start) { this.start = start; }

    public Date getEnd() { return end; }

    public void setEnd(Date end) { this.end = end; }

    public String getEventID() { return eventID; }

    public void setEventID(String eventID) { this.eventID = eventID; }

    public ArrayList<String> getHosts() { return hosts; }

    public void setHosts(ArrayList<String> hosts) { this.hosts = hosts; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
