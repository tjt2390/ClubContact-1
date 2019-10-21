package com.example.eventstrackerapp.ui.carpool;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eventstrackerapp.R;

public class CarpoolFragment extends Fragment {

    private CarpoolViewModel carpoolViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carpoolViewModel =
                ViewModelProviders.of(this).get(CarpoolViewModel.class);
        View root = inflater.inflate(R.layout.fragment_carpool, container, false);

        Button campusCarpool = (Button) root.findViewById(R.id.campusCarpoolButton);
        campusCarpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCampusCarpool = new Intent(getContext(), CampusCarpoolActivity.class);
                startActivity(gotoCampusCarpool);
            }
        });

        return root;
    }
}
