package com.example.eventstrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventstrackerapp.authentication.LoginActivity;
import com.example.eventstrackerapp.authentication.SignUpActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button goToSignUp;
    Button goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        goToSignUp = findViewById(R.id.welcomeGoToSignUp);
        goToLogin = findViewById(R.id.welcomeGoToLogin);

        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });
    }
}
