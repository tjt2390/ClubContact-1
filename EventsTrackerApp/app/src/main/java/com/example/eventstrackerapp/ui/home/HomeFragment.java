package com.example.eventstrackerapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.annotation.Nullable;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private ListView eventsListView;
    private ArrayList<String> events = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> startDates = new ArrayList<>();
    private ArrayList<String> startTimes = new ArrayList<>();
    private ArrayList<String> endDates = new ArrayList<>();
    private ArrayList<String> endTimes = new ArrayList<>();
    private ArrayList<ArrayList<String>> hosts = new ArrayList<>(); // the host(s) of every event

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference eventsRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        eventsListView = root.findViewById(R.id.eventListView);

        // Input all Documents from the Events collection into the events array
        eventsRef = firebaseFirestore.collection("Events");
        eventsRef.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    return;
                }
                else{
                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                        Event event = documentSnapshot.toObject(Event.class);
                        if(event != null){
                            events.add(event.getTitle());
                            locations.add(event.getLocation());
                            startDates.add(event.getStartDate());
                            startTimes.add(event.getStartTime());
                            endDates.add(event.getEndDate());
                            endTimes.add(event.getEndTime());
                            ArrayList<String> eventHosts = event.getHosts(); // one event's hosts
                            hosts.add(eventHosts);
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        });

        if(events.size() > 0){ // if the array is not empty
            EventAdapter eventAdapter = new EventAdapter(getContext(), events, locations, startDates,
                    startTimes, endDates, endTimes, hosts);
            eventsListView.setAdapter(eventAdapter);
        }
        else{
            Toast.makeText(getContext(),"There are no upcoming events", Toast.LENGTH_SHORT).show();

        }

        return root;
    }
}