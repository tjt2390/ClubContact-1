package com.example.eventstrackerapp.ui.home.upcoming.details;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eventstrackerapp.R;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsHostAdapter extends BaseAdapter {
    private static final String TAG = "EventDetailsHostAdapter";
    LayoutInflater mInflater;
    ArrayList<String> hosts; // all the hosts of a single event

    public EventDetailsHostAdapter(Context context, ArrayList<String> hosts) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.hosts = hosts;
    }

    @Override
    public int getCount() {
        return hosts.size();
    }

    @Override
    public Object getItem(int position) {
        return hosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.activity_event_details_host, null);
        TextView hostNameTV = v.findViewById(R.id.event_details_host_name);
        Button subscribe = v.findViewById(R.id.event_details_subscribe_button);

        final String hostName = hosts.get(position);

        hostNameTV.setText(hostName);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ToDo: SAVE NAME OF HOST TO THE SUBSCRIPTIONS LIST HERE

            }
        });

        return v;
    }
}
