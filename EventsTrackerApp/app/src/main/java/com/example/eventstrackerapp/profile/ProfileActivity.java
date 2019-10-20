package com.example.eventstrackerapp.profile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.eventstrackerapp.R;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    //Instance Variables
    TextView username;
    TextView password;
    TextView email;
    TextView name;
    TextView graduationYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
