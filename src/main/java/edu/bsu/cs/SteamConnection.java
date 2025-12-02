package edu.bsu.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class SteamConnection {

    private static final String KEY = ConfigLoader.get("steam.api.key");
    private static final String USER_AGENT = "CS222FinalProject/0.1 (caleb.langley@bsu.edu)";

    public static String getAccountId(String accountName) throws SteamApiException {
        if (accountName.matches("\\d+")) {
            return accountName;
        }

        try {
            URI uri = new URI("https://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/" + "?key=" + KEY + "&vanityurl=" + accountName);
            String response = fetchUrlAsString(uri);
            return AccountParser.parseAccountId(response);
        } catch (URISyntaxException | IOException e) {
            throw new SteamApiException("Failed to resolve Steam account ID.", e);
        }
    }

    public static URLConnection connectToUser(String accountID) throws IOException, URISyntaxException {
        String urlString = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/" + "?key=" + KEY + "&steamids=" + accountID;
        return openConnection(urlString);
    }

    public static URLConnection connectToGames(String accountID) throws IOException, URISyntaxException {
        String urlString = "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/" + "?key=" + KEY + "&steamid=" + accountID + "&include_appinfo=1&include_played_free_games=1&format=json";
        return openConnection(urlString);
    }

    public static URLConnection connectToRecentlyPlayed(String accountID) throws IOException, URISyntaxException {
        String urlString = "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/" + "?key=" + KEY + "&steamid=" + accountID + "&count=5&format=json";
        return openConnection(urlString);
    }

    private static URLConnection openConnection(String urlString) throws IOException, URISyntaxException {
        URI uri = new URI(urlString);
        URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.connect();
        return connection;
    }

    private static String fetchUrlAsString(URI uri) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}
