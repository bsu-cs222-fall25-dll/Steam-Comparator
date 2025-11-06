package edu.bsu.cs;

public record Game(int minutes, int appID, String gameName) {

    public String printGame() {
        return ("Game Name: " + gameName() + ", App ID: " + appID() + ", Hours played: " + minutes() / 60);
    }
}
