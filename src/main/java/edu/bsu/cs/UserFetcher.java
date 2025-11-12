package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class UserFetcher {
    public static String getUserDataAsString(String accountName) throws SteamApiException {
        try {
            String accountID = SteamConnection.getAccountId(accountName);
            URLConnection connection = SteamConnection.connectToUser(accountID);
            return readJsonAsString(connection);
        } catch (Exception e) {
            throw new SteamApiException("Failed to fetch user data.", e);
        }
    }

    public static String getOwnedGamesAsString(String accountName) throws SteamApiException {
        try {
            String accountID = SteamConnection.getAccountId(accountName);
            URLConnection connection = SteamConnection.connectToGames(accountID);
            return readJsonAsString(connection);
        } catch (Exception e) {
            throw new SteamApiException("Failed to fetch game data.", e);
        }
    }

    public static String getRecentlyPlayedDataAsString(String accountName) throws SteamApiException {
        try {
            String accountID = SteamConnection.getAccountId(accountName);
            URLConnection connection = SteamConnection.connectToRecentlyPlayed(accountID);
            return readJsonAsString(connection);
        } catch (Exception e) {
            throw new SteamApiException("Failed to fetch recently played games data.", e);
        }
    }

    public static String readJsonAsString(URLConnection connection) throws SteamApiException {
        try (InputStream input = connection.getInputStream()) {
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SteamApiException("Error reading JSON response.", e);
        }
    }
}
