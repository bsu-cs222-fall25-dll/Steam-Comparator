package edu.bsu.cs;

import java.util.ArrayList;

public class User {
    private String steamID;
    private String displayName;
    private ArrayList<Game> games = new ArrayList<>();

    public User(String steamID, String displayName){
        this.steamID = steamID;
        this.displayName = displayName;
    }
}
