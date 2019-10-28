package com.example.eventstrackerapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.eventstrackerapp.Event;
import com.example.eventstrackerapp.MainActivity;
import com.example.eventstrackerapp.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.annotation.Nullable;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private static final String TAG = "HomeFragment";

    private ListView eventsListView;
    private ArrayList<String> events;
    private ArrayList<String> locations;
    private ArrayList<String> startDates;
    private ArrayList<String> startTimes;
    private ArrayList<String> endDates;
    private ArrayList<String> endTimes;
    private ArrayList<ArrayList<String>> hosts; // the host(s) of every event
    private ArrayList<String> descriptions;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference eventsRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        eventsListView = root.findViewById(R.id.eventListView);

        events = new ArrayList<>();
        locations = new ArrayList<>();
        startDates = new ArrayList<>();
        startTimes = new ArrayList<>();
        startTimes = new ArrayList<>();
        endDates = new ArrayList<>();
        endTimes = new ArrayList<>();
        hosts = new ArrayList<>();
        descriptions = new ArrayList<>();

        eventsRef = firebaseFirestore.collection("Events");
        // Input all Documents from the Events collection into the events array

        eventsRef.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    return;
                }
                else{
                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                        Event event = documentSnapshot.toObject(Event.class);
                        events.add(event.getTitle());
                        Log.i(TAG, "Size is now... " + events.size() + "");
                        locations.add(event.getLocation());
                        startDates.add(event.getStartDate());
                        startTimes.add(event.getStartTime());
                        endDates.add(event.getEndDate());
                        endTimes.add(event.getEndTime());
                        ArrayList<String> eventHosts = event.getHosts(); // one event's hosts
                        hosts.add(eventHosts);
                        descriptions.add(event.getDescription());
                    }

                    Log.i(TAG, "SIZE OF EVENTS IS: " + events.size() + "");
                    if(events.size() > 0){ // if the array is not empty
                        EventAdapter eventAdapter = new EventAdapter(getContext(), events, locations, startDates,
                                startTimes, endDates, endTimes, hosts);
                        eventsListView.setAdapter(eventAdapter);

                        // By now there should be event(s) listed in the homepage
                        // Make each event clickable to view its details
                        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent showDetails = new Intent(getContext(), EventDetailsActivity.class);

                                TextView nameTV = view.findViewById(R.id.home_event_name);
                                TextView locationTV = view.findViewById(R.id.home_event_location);
                                TextView timeTV = view.findViewById(R.id.home_event_time);
                                TextView dateTV = view.findViewById(R.id.home_event_date);

                                String name = nameTV.getText().toString();
                                String location = locationTV.getText().toString();
                                String[] times = timeTV.getText().toString().split("-");
                                String startTime = times[0];
                                String endTime = times[1];
                                String[]dates = dateTV.getText().toString().split("-");
                                String startDate = dates[0];
                                String endDate = dates[1];

                                showDetails.putExtra("com.example.eventstrackerapp.ui.home.EVENT_INDEX", position); // pass into the intent which event the user chose to view details on. Referenced by EVENT_INDEX
                                showDetails.putExtra("com.example.eventstrackerapp.ui.home.EVENT_NAME", name); // pass into the intent the event's name.
                                showDetails.putExtra("com.example.eventstrackerapp.ui.home.EVENT_LOCATION", location);
                                showDetails.putExtra("com.example.eventstrackerapp.ui.home.EVENT_START_TIME", startTime); // pass the start time
                                showDetails.putExtra("com.example.eventstrackerapp.ui.home.EVENT_START_DATE", startDate); // pass the start date
                                showDetails.putExtra("com.example.eventstrackerapp.ui.home.EVENT_END_TIME", endTime); // pass the end time
                                showDetails.putExtra("com.example.eventstrackerapp.ui.home.EVENT_END_DATE", endDate); // pass the end date

                                startActivity(showDetails);
                            }
                        });
                    }
                    else{
                        Toast.makeText(getContext(),"There are no upcoming events", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return root;
    }

}