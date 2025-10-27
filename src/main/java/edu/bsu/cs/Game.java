package edu.bsu.cs;

public class Game {
    private int minutes;
    private int appID;
    private  String gameName;

    public Game(int minutes, int appID, String gameName) {
        this.minutes = minutes;
        this.appID = appID;
        this.gameName = gameName;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getAppID() {
        return appID;
    }

    public String getGameName() {
        return gameName;
    }

    public String printGame(){
        return ("Game Name: " + getGameName() + ", App ID: " + getAppID() + ", Hours played: " + getMinutes()/60);
    }
}
