package edu.bsu.cs;

public record User(String steamID, String displayName, Game games) {

    public String printUser() {
        return ("User: " + displayName() + ", User ID: " + steamID() + "\n" +
                games.printGame());
    }
}
