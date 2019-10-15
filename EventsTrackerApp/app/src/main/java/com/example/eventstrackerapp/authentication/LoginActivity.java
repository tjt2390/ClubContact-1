package com.example.eventstrackerapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.eventstrackerapp.MainActivity;
import com.example.eventstrackerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {
    // Loading
    ProgressBar progressBar;

    // UI Elements Instance Variables
    EditText email;
    EditText password;
    Button login;
    TextView goToSignUp;

    // Firebase Instance
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Instances
        progressBar = findViewById(R.id.loginProgressBar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        goToSignUp = findViewById(R.id.goToSignUp);

        firebaseAuth = FirebaseAuth.getInstance();

        // Set up a listener for the login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // first check if there are any empty fields
                String mail = email.getText().toString();
                String pwd = password.getText().toString();
                if(mail.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill out any empty fields", Toast.LENGTH_LONG).show();
                }
                else{
                    // set progress bar to visible to tell user to wait
                    progressBar.setVisibility(View.VISIBLE);
                    // Get the Email and Password the user inputted
                    // make all buttons invisible so user will not interfere with loading process
                    login.setVisibility(View.GONE);
                    goToSignUp.setVisibility(View.GONE);
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        // ====== ADD HERE: Get the First Name, Last Name, and Grad Year ======

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // set progress bar to gone to tell user its ready
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){ // if task succeeds...
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                                // Switch screens
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            else{// if task fails...
                                login.setVisibility(View.VISIBLE);
                                goToSignUp.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        // Set up a listener for the goToSignUp link
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUpActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(goToSignUpActivity);
            }
        });
    }
}
