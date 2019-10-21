package com.example.eventstrackerapp.profile;
import java.util.ArrayList;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class User {
    private String name;
    //private ArrayList<Event> rsvpList;
    private ArrayList<Club> clubList;
    private int gradYear;
    private String email;
    private String username, password;
    //private ArrayList<Carpool> carList;
    private String userType;
    //NOTE: Use DatabaseReference class to connect to Firebase
    private DatabaseReference databaseReference;
    private ArrayList<Task> tasks;

    public User(String userName)
    {
        this(userName, "user");
    }

    /**
     *
     * @param username
     * @param userType
     */
    public User(String username, String userType)
    {
        this.username = username;
        this.userType = userType;
    }

    public String getName()
    {
        return name;
    }

    public int getGradYear()
    {
        return gradYear;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserType()
    {
        return userType;
    }
    private String getEmail()
    {
        return email;
    }
}
