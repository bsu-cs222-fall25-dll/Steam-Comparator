package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;

public class UserFetcher {
    public static String getUserDataAsString(String accountName) throws Exception {
        URLConnection connection = SteamConnection.connectToUser(SteamConnection.getAccountId(accountName));
        return Formatter.readJsonAsString(connection);
    }

    public static String getGameDataAsString(String accountName) throws Exception {
        URLConnection connection = SteamConnection.connectToGames(SteamConnection.getAccountId(accountName));
        return Formatter.readJsonAsString(connection);
    }
}
