package com.example.eventstrackerapp.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eventstrackerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.Nullable;

public class EventDetailsActivity extends AppCompatActivity {

    private static final String TAG = "EventDetailsActivity";
    FirebaseFirestore firebaseFirestore;
    private CollectionReference eventsRef;

    LinearLayout hostsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        firebaseFirestore = FirebaseFirestore.getInstance();
        eventsRef = firebaseFirestore.collection("Events");

        hostsLayout=(LinearLayout)findViewById(R.id.event_details_hosts_layout);

        Intent in = getIntent();
        int index = in.getIntExtra("com.example.eventstrackerapp.ui.home.EVENT_INDEX", -1);
        String name = in.getStringExtra("com.example.eventstrackerapp.ui.home.EVENT_NAME");
        String location = in.getStringExtra("com.example.eventstrackerapp.ui.home.EVENT_LOCATION");
        String startTime = in.getStringExtra("com.example.eventstrackerapp.ui.home.EVENT_START_TIME");
        String startDate = in.getStringExtra("com.example.eventstrackerapp.ui.home.EVENT_START_DATE");
        String endTime = in.getStringExtra("com.example.eventstrackerapp.ui.home.EVENT_END_TIME");
        String endDate = in.getStringExtra("com.example.eventstrackerapp.ui.home.EVENT_END_DATE");

        if(index > -1){ // if the user chooses an event that exists in the homepage
            setEventName(name, location, startTime, startDate, endTime, endDate);
        }
    }

    private void setEventName(String name, String location, String startTime, String startDate, String endTime, String endDate){
        TextView nameTV = findViewById(R.id.event_details_name); // name of the event
        TextView locationTV = findViewById(R.id.event_details_location);
        TextView startEndTimeDateTV = findViewById(R.id.event_details_time_date);

        // set parameters into their corresponding text views
        nameTV.setText(name);
        locationTV.setText(location);
        String timeDateFormat = startTime + " - " + endTime + "\n" + startDate + " - " + endDate;
        startEndTimeDateTV.setText(timeDateFormat);

        // get the other information of the event from the database
        // NOTE: the id should be the same as the name of the event
        // use the adapter to set the host names
        eventsRef.document(name).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();

                // ADD HOST NAMES AND SUBSCRIPTION BUTTONS
                List<String> hosts = (List<String>) documentSnapshot.get("hosts");
                EventDetailsHostAdapter eventDetailsHostAdapter = new EventDetailsHostAdapter(getApplicationContext(), (ArrayList<String>) hosts);
                for(int i=0; i<hosts.size(); i++){
                    Log.i(TAG, "ArrayList Element: " + hosts.get(i));
                    View v = eventDetailsHostAdapter.getView(i, null, null);
                    hostsLayout.addView(v);
                }

                // ADD DESCRIPTION
                TextView descriptionTV = findViewById(R.id.event_details_description);
                descriptionTV.setText((String)documentSnapshot.get("description"));
            }
        });

    }
}
