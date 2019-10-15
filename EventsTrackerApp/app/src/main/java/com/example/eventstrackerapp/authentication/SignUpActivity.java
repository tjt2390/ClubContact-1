package com.example.eventstrackerapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventstrackerapp.MainActivity;
import com.example.eventstrackerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    // Loading
    ProgressBar progressBar;

    // UI Elements Instance Variables
    EditText email;
    EditText password;
    EditText firstName;
    EditText lastName;
    EditText gradYear;
    Button signUp;
    TextView goToLogin;

    // Firebase Instance
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Instances
        progressBar = findViewById(R.id.signupProgressBar);
        email = findViewById(R.id.newEmail);
        password = findViewById(R.id.newPassword);
        firstName = findViewById(R.id.newFirstName);
        lastName = findViewById(R.id.newLastName);
        gradYear = findViewById(R.id.newGradYear);
        signUp = findViewById(R.id.signup);
        goToLogin = findViewById(R.id.goToLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        // Set up a listener for the sign up button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // first check if there are any empty fields
                String mail = email.getText().toString();
                String pwd = password.getText().toString();
                if(mail.isEmpty() || pwd.isEmpty()){ //====== ADD HERE: check if firstName, lastName, or gradYear are empty ======
                    Toast.makeText(SignUpActivity.this, "Please fill out any empty fields", Toast.LENGTH_LONG).show();
                }
                else{
                    // set progress bar to visible to tell user to wait
                    progressBar.setVisibility(View.VISIBLE);
                    // make all buttons invisible so user will not interfere with loading process
                    signUp.setVisibility(View.GONE);
                    goToLogin.setVisibility(View.GONE);
                    // Get the Email and Password the user inputted
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // set progress bar to gone to tell user its ready
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                //====== ADD HERE: Save the First Name, Last Name, and Grad Year in Database ======
                                Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            }
                            else{
                                signUp.setVisibility(View.VISIBLE);
                                goToLogin.setVisibility(View.VISIBLE);
                                Toast.makeText(SignUpActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        // Set up a listener for the goToLogin link
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginActivity = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(goToLoginActivity);
            }
        });
    }
}
