package edu.bsu.cs;

import java.util.ArrayList;

public class User {
    private static String steamID;
    private static String displayName;
    private ArrayList<Game> games = new ArrayList<>();

    public User(String steamID, String displayName){
        User.steamID = steamID;
        User.displayName = displayName;
    }

    public static String getSteamID() {
        return steamID;
    }

    public static String getDisplayName() {
        return displayName;
    }

    public static String printUser() {
        return ("User: " + getDisplayName() + ", User ID: " + getSteamID());
    }
}
