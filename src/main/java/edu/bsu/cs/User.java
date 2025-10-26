package edu.bsu.cs;

public class User {
    private static String steamID;
    private static String displayName;
    private static Game games;

    public User(String steamID, String displayName, Game games){
        User.steamID = steamID;
        User.displayName = displayName;
        User.games = games;
    }

    public static String getSteamID() {
        return steamID;
    }

    public static String getDisplayName() {
        return displayName;
    }

    public static Game getGames() {
        return games;
    }

    public static String printUser() {
        return ("User: " + getDisplayName() + ", User ID: " + getSteamID() + "\n" +
                Game.printGame());
    }
}
