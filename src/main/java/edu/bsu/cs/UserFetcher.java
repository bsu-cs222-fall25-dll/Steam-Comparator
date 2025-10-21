package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;

public class UserFetcher {
    public static String getUserDataAsString(String accountName) throws Exception, IOException, URISyntaxException {
        URLConnection connection = SteamConnection.connectToUser(SteamConnection.getAccountId(accountName));
        return Formatter.readJsonAsString(connection);
    }
}
