package edu.bsu.cs;

public record Game(int minutes, int appID, int lastPlayedTimestamp, String gameName, String imageURL) {
    public String printGame() {
        return (gameName() + ": " + String.format("%.1f", minutes() / 60.0) + " hours");
    }
}
