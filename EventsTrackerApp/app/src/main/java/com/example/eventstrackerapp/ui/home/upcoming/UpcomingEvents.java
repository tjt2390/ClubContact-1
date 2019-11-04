package com.example.eventstrackerapp.ui.home.upcoming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.eventstrackerapp.Event;
import com.example.eventstrackerapp.R;

import java.util.ArrayList;
import java.util.Date;

public class UpcomingEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        ArrayList<Event> events = fillWithData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.upcoming_events_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewEventsAdapter adapter = new RecyclerViewEventsAdapter(getApplicationContext(), events);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Event> fillWithData(){

        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event("", "Walkalothon", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Festival of Cultures", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Hackathon", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Movie Watch", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Tommie Johnnie Football", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Dancing Day", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "FAE", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Art Museum", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Pizza Night", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Mall Visit", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Sales at Bookstore", "", new Date(), new Date(), new ArrayList<String>(), ""));
        events.add(new Event("", "Parents Day", "", new Date(), new Date(), new ArrayList<String>(), ""));

        return events;
    }
}
