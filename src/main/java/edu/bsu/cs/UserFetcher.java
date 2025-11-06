package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class UserFetcher {
    public static String getUserDataAsString(String accountName) throws Exception {
        try {
            URLConnection connection = SteamConnection.connectToUser(SteamConnection.getAccountId(accountName));
            return readJsonAsString(connection);
        } catch (IOException | URISyntaxException e) {
            throw new SteamApiException("Failed to fetch user data.", e);
        }
    }

    public static String getGameDataAsString(String accountName) throws Exception {
        try {
            URLConnection connection = SteamConnection.connectToGames(SteamConnection.getAccountId(accountName));
            return readJsonAsString(connection);
        } catch (IOException | URISyntaxException e) {
            throw new SteamApiException("Failed to fetch game data.", e);
        }

    }

    public static String readJsonAsString(URLConnection connection) throws IOException, SteamApiException {
        try (InputStream input = connection.getInputStream()) {
            return new String(input.readAllBytes(), Charset.defaultCharset());
        }  catch (IOException e) {
            throw new SteamApiException("Error reading JSON response.", e);
        }
    }
}
