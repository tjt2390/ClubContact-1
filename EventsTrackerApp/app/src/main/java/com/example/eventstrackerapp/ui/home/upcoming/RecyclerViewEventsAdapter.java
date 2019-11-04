package com.example.eventstrackerapp.ui.home.upcoming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventstrackerapp.Event;
import com.example.eventstrackerapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewEventsAdapter extends RecyclerView.Adapter<RecyclerViewEventsAdapter.EventsViewHolder> {

    private ArrayList<Event> mEventsSet;
    private Context context;
    private int itemPosition;

    public RecyclerViewEventsAdapter(Context context, ArrayList<Event> mEventsSet){
        this.mEventsSet = mEventsSet;
        this.context = context;
    }

    @Override
    public RecyclerViewEventsAdapter.EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        EventsViewHolder viewHolder = new EventsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.title.setText(mEventsSet.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mEventsSet.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insert(int position, Event event){
        mEventsSet.add(position, event);
        notifyItemInserted(position);
    }

    public void remove(Event event){
        int position = mEventsSet.indexOf(event);
        mEventsSet.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Inner class for ViewHolder
     */


    public class EventsViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView title;

        public EventsViewHolder(View itemView){
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_row_layout);
            title = (TextView) itemView.findViewById(R.id.event_title);
        }
    }


}
