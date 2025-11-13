package edu.bsu.cs;

public record Game(int minutes, int appID, String gameName) {
    public String printGame() {
        return (gameName() + ": " + String.format("%.2f", minutes() / 60.0) + " hours");
    }
}
