package edu.bsu.cs;

public class User {
    private String steamID;
    private String displayName;
    private Game games;

    public User(String steamID, String displayName, Game games){
        this.steamID = steamID;
        this.displayName = displayName;
        this.games = games;
    }

    public String getSteamID() {
        return steamID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String printUser() {
        return ("User: " + getDisplayName() + ", User ID: " + getSteamID() + "\n" +
                games.printGame());
    }
}
