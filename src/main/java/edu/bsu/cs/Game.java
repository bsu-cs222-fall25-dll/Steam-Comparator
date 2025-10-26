package edu.bsu.cs;

public class Game {
    private static int minutes;
    private static int appID;
    private static String gameName;

    public Game(int minutes, int appID, String gameName) {
        Game.minutes = minutes;
        Game.appID = appID;
        Game.gameName = gameName;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static int getAppID() {
        return appID;
    }

    public static String getGameName() {
        return gameName;
    }

    public static String printGame(){
        return ("Game Name: " + getGameName() + ", App ID: " + getAppID() + ", Hours played: " + getMinutes()/60);
    }
}
