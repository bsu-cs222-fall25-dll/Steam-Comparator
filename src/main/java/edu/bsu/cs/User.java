package edu.bsu.cs;

public record User(String steamID, String displayName, Game games) {

    public String printUser() {
        return ("User: " + displayName() + "\nUser ID: " + steamID() + "\n" +
                games.printGame());
    }
}
