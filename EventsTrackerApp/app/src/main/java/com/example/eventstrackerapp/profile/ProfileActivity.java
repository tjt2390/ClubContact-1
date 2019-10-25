package com.example.eventstrackerapp.profile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.eventstrackerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    //Instance Variables
    private TextView userID;
    private TextView textViewPassword;
    private TextView textViewName;
    private TextView textViewEmail;
    private TextView textViewGradYear;
    private TextView textViewUserType;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference allUsersRef = db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userID  = findViewById(R.id.profileUsername);
        textViewPassword = findViewById(R.id.profilePassword);
        textViewName = findViewById(R.id.profileName);
        textViewEmail = findViewById(R.id.profileEmail);
        textViewGradYear = findViewById(R.id.profileGradYear);
        textViewUserType = findViewById(R.id.profileUserType);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String id = firebaseAuth.getCurrentUser().getUid();
        DocumentReference userRef = allUsersRef.document(id);

        userRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Toast.makeText(ProfileActivity.this,"Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                    return;
                }
                if(documentSnapshot.exists()){
//                    String title = documentSnapshot.getString(KEY_TITLE);
//                    String description = documentSnapshot.getString(KEY_DESCRIPTION);

                    // Here, we use the Note class instead
                    User user = documentSnapshot.toObject(User.class);
                    String username = user.getUserID();
                    String password = user.getPassword();
                    String name = user.getFirstName() + " " + user.getLastName();
                    String email = user.getEmail();
                    int year = user.getGradYear();
                    String type = user.getUserType();

                    userID.setText("Name: " + username);
                    textViewPassword.setText("Username: " + password);
                    textViewName.setText("Password: " + name);
                    textViewEmail.setText("Email: " + email);
                    textViewGradYear.setText("Graduation Year: " + year);
                    textViewUserType.setText("User Type: " + type);
                } else{ // else if the snapshot is deleted or does not exist in the first place...
                    userID.setText("");
                    textViewPassword.setText("");
                    textViewName.setText("");
                    textViewEmail.setText("");
                    textViewGradYear.setText(0 + "");
                    textViewUserType.setText("");
                }
            }
        });
    }
}
