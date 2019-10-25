package com.example.eventstrackerapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eventstrackerapp.R;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    ArrayList<String> events;
    ArrayList<String> locations;
    ArrayList<String> startDates;
    ArrayList<String> startTimes;
    ArrayList<String> endDates;
    ArrayList<String> endTimes;
    ArrayList<ArrayList<String>> hosts;

    public EventAdapter(Context context, ArrayList<String> events, ArrayList<String> locations,
                        ArrayList<String> startDates, ArrayList<String> startTimes, ArrayList<String> endDates,
                        ArrayList<String> endTimes, ArrayList<ArrayList<String>> hosts) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.events = events;
        this.locations = locations;
        this.startDates = startDates;
        this.startTimes = startTimes;
        this.endDates = endDates;
        this.endTimes = endTimes;
        this.hosts = hosts;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.home_event_details_list, null);
        TextView nameTV = v.findViewById(R.id.home_event_name);
        TextView locationTV = v.findViewById(R.id.home_event_location);
        TextView hostsTV = v.findViewById(R.id.home_event_clubs);
        TextView sTimeDateTV = v.findViewById(R.id.home_event_start);
        TextView eTimeDateTV = v.findViewById(R.id.home_event_end);

        String name = events.get(position);
        String loc = locations.get(position);
        String sdate = startDates.get(position);
        String stime = startTimes.get(position);
        String edate = endDates.get(position);
        String etime = endTimes.get(position);

        // Get all the hosts of an event
        String clubHosts = "";
        ArrayList<String> eventHosts = hosts.get(position);
        for(String host : eventHosts){
            clubHosts += host;
            if(eventHosts.size() > 1){
                clubHosts += ", ";
            }
        }

        nameTV.append(name);
        locationTV.append(loc);
        sTimeDateTV.append(stime + "\t\t\t" + sdate);
        eTimeDateTV.append(etime + "\t\t\t" + edate);
        hostsTV.append(clubHosts);

        return null;
    }
}
