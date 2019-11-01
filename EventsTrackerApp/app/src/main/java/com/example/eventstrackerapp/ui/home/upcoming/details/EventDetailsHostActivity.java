package com.example.eventstrackerapp.ui.home.upcoming.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.eventstrackerapp.R;

public class EventDetailsHostActivity extends AppCompatActivity {

    //ToDo: (Optional) when the use presses on the name of the host, they are led to a screen where they can view the details of the club
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_host);
    }
}
