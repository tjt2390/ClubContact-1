package com.example.eventstrackerapp.profile;
import java.util.ArrayList;

public class User {
    private String name;
    //private ArrayList<Event> rsvpList;
    private ArrayList<Club> clubList;
    private int gradYear;
    private String username, password;
    //private ArrayList<Carpool> carList;
    private String userType;
    //NOTE: Use DatabaseReference class to connect to Firebase

    public User(String userName)
    {
        this(userName, "USER");
    }

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
}
