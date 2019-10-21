package com.example.eventstrackerapp.profile;

public class ClubExec extends User {

    private Club club;
    private String position;

    public ClubExec(String username)
    {
        super(username, "exec");
    }

    public Club getClub()
    {
       return this.club;
    }

    public String getPosition()
    {
        return this.position;
    }
}
