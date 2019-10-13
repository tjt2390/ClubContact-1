package com.example.eventstrackerapp.ui.subscriptions;

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

public class SubscriptionsFragment extends Fragment {

    private SubscriptionsViewModel subscriptionsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        subscriptionsViewModel =
                ViewModelProviders.of(this).get(SubscriptionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_subscriptions, container, false);
        final TextView textView = root.findViewById(R.id.text_subscriptions);
        subscriptionsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
