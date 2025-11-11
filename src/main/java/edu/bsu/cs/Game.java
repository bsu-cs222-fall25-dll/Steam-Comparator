package edu.bsu.cs;

public record Game(int minutes, int appID, String gameName) {

    public String printGame() {
        return ("Game Name: " + gameName() + ",\nApp ID: " + appID() + ",\nHours played: " + minutes() / 60);
    }
}
