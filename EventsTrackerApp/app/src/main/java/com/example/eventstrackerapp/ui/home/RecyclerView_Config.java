package com.example.eventstrackerapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventstrackerapp.Event;
import com.example.eventstrackerapp.R;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private EventAdapter mEventAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Event> events, List<String> keys){
        mContext = context;
        mEventAdapter = new EventAdapter(events, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mEventAdapter);
    }

    /**
     * Inner class EventItemView
     * Extends to the view holder for a recycler view (list format for xml)
     */
    class EventItemView extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mLocation;
        private TextView mStartTime;
        private TextView mEndTime;

        private String key;

        public EventItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.event_list_item, parent, false));

            mTitle = (TextView) itemView.findViewById(R.id.title_textview);
            mLocation = (TextView) itemView.findViewById(R.id.location_textview);
            mStartTime = (TextView) itemView.findViewById(R.id.starttime_textview);
            mEndTime = (TextView) itemView.findViewById(R.id.endtime_textview);
        }

        public void bind(Event event, String key){
            mTitle.setText(event.getTitle());
            mLocation.setText(event.getLocation());
            mStartTime.setText(event.getStartDate());
            mEndTime.setText(event.getEndDate());
            this.key = key;
        }
    }

    class EventAdapter extends RecyclerView.Adapter<EventItemView>{
        private List<Event> mEventList;
        private List<String> mKeys;

        public EventAdapter(List<Event> mEventList, List<String> mKeys) {
            this.mEventList = mEventList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public EventItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new EventItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull EventItemView holder, int position) {
            holder.bind(mEventList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }
    }
}
