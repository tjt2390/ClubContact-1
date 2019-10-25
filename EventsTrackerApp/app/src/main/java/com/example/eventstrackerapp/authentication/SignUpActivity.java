package com.example.eventstrackerapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventstrackerapp.MainActivity;
import com.example.eventstrackerapp.R;
import com.example.eventstrackerapp.profile.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    // Loading
    private ProgressBar progressBar;

    // UI Elements Instance Variables
    private EditText email;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText gradYear;
    private Button signUp;
    private TextView goToLogin;

    // Firebase Instance
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference usersRef;


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
        firebaseFirestore = FirebaseFirestore.getInstance();
        usersRef = firebaseFirestore.collection("Users");

        // Set up a listener for the goToLogin link
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginActivity = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(goToLoginActivity);
            }
        });
    }
    public void addUser(View v){
        // 1) Check if any of the fields are empty
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() ||
                lastName.getText().toString().isEmpty() || gradYear.getText().toString().isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please fill out empty fields", Toast.LENGTH_LONG).show();
        } else{ // 2) Tell User to wait
            progressBar.setVisibility(View.VISIBLE);
            signUp.setVisibility(View.GONE);
            goToLogin.setVisibility(View.GONE);
            // 3) Get the Email and Password the user inputted and check if it is new
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // 4) Tell User whether it is successful or not
                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        // 5) Set the information into FireStore database
                        String mail = email.getText().toString();
                        String pwd = password.getText().toString();
                        String fname = firstName.getText().toString();
                        String lname = lastName.getText().toString();
                        String year = gradYear.getText().toString();
                        String documentName = firebaseAuth.getCurrentUser().getUid(); // get the UID from fireAuth

                        User newUser = new User(mail, pwd, fname, lname, Integer.parseInt(year), "default", documentName);

                        usersRef.document(documentName).set(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SignUpActivity.this,"User saved", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this,"Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });

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

}
