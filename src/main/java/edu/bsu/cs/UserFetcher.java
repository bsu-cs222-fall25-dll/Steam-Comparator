package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class UserFetcher {

    public static String readJsonAsString(URLConnection connection) throws SteamApiException {
        try (InputStream input = connection.getInputStream()) {
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SteamApiException("Error reading JSON response.", e);
        }
    }


    @FunctionalInterface
    interface Connector {
        URLConnection connect(String accountID) throws IOException, URISyntaxException;
    }

    private static String fetchData(String accountName, Connector connector) throws SteamApiException {
        try {
            String accountID = SteamConnection.getAccountId(accountName);
            URLConnection connection = connector.connect(accountID);
            return readJsonAsString(connection);
        } catch (Exception e) {
            throw new SteamApiException("Failed to fetch data", e);
        }
    }

    public static String getUserDataAsString(String accountName) throws SteamApiException {
        return fetchData(accountName, SteamConnection::connectToUser);
    }

    public static String getOwnedGamesAsString(String accountName) throws SteamApiException {
        return fetchData(accountName, SteamConnection::connectToGames);
    }

    public static String getRecentlyPlayedDataAsString(String accountName) throws SteamApiException {
        return fetchData(accountName, SteamConnection::connectToRecentlyPlayed);
    }
}
