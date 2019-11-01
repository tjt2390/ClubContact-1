package com.example.eventstrackerapp.ui.allUsers;

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

public class ViewAllUsersFragment extends Fragment {

    private ViewAllUsersViewModel viewAllUsersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        viewAllUsersViewModel =
                ViewModelProviders.of(this).get(ViewAllUsersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_viewallusers, container, false);
        final TextView textView = root.findViewByld(R.id.text_viewAllUsers);
        viewAllUsersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s){
                textView.setText(s);
            }

        });
        return root;
    }
}
