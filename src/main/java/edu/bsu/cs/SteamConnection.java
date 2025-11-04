package edu.bsu.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class SteamConnection {
    public static String getAccountId(String accountName) throws Exception {
        if (accountName.matches("\\d+")) {
            return accountName;
        }

        URL url = new URL("https://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=F2B3A13F8246165E1FD566131CB5A81F&vanityurl=" + accountName);

       try {
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           conn.setRequestProperty("User-Agent", "CS222FinalProject/0.1 (caleb.langley@bsu.edu)");

           BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           StringBuilder response = new StringBuilder();
           String line;
           while ((line = reader.readLine()) != null) {
               response.append(line);
           }
           reader.close();
           return AccountParser.parseAccountId(response.toString());

       } catch (IOException e) {
           throw new SteamApiException("Failed to connect to Steam API.", e);
       }


    }

    public static URLConnection connectToUser(String accountID) throws IOException, URISyntaxException {
        String URLString = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=F2B3A13F8246165E1FD566131CB5A81F&steamids=" + accountID;
        URI uri = new URI(URLString);
        URLConnection connection;

        try {
            connection = uri.toURL().openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        connection.connect();
        return connection;
    }

    public static URLConnection connectToGames(String accountID) throws URISyntaxException, IOException {
        String URLString = "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=F2B3A13F8246165E1FD566131CB5A81F&steamid="+accountID+"&include_appinfo=1&include_played_free_games=1&format=json";
        URI uri = new URI(URLString);
        URLConnection connection = uri.toURL().openConnection();

        connection.connect();
        return connection;
    }
}
