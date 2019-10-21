package com.example.eventstrackerapp;

import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceEvents;
    private List<Event> allEvents = new ArrayList<>(); //List for EVENTS

    public interface DataStatus{
        void DataIsLoaded(List<Event> allEvents, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceEvents = mDatabase.getReference("events"); //Reference for EVENTS
    }

    /**
     * When you call readEvents, the value of that listener object will be attached
     * to the mReferenceEvents. However, onDataChange method will not be executed at
     * the same time (it is a synchronous method/it has different process).
     * To link the processes, we need and interface.
     *
     * This method fetches information on events from the realtime database
     */
    //Method for EVENTS
    public void readEvents(final DataStatus dataStatus){
        mReferenceEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allEvents.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Event event = keyNode.getValue(Event.class);
                    allEvents.add(event);
                }
                dataStatus.DataIsLoaded(allEvents, keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
