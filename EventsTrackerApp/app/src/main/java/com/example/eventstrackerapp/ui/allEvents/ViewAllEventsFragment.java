package com.example.eventstrackerapp.ui.allEvents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eventstrackerapp.R;

public class ViewAllEventsFragment extends Fragment {

    private ViewAllEventsViewModel viewAllEventsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        viewAllEventsViewModel =
                ViewModelProviders.of(this).get(ViewAllEventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_viewallusers, container, false);
        final TextView textView = root.findViewById(R.id.nav_viewAllEvents);
        viewAllEventsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s){
                textView.setText(s);
            }

        });
        return root;
    }
}
