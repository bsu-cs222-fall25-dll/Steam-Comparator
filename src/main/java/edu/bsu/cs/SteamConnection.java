package edu.bsu.cs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SteamConnection {
/*
getAccountId and parseAccountId were
made using a guide, so we should probably
find out how exactly they work and
see if they need refactoring
 */
    public static String getAccountId() throws Exception {

        URL url = new URL("https://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=F2B3A13F8246165E1FD566131CB5A81F&vanityurl=tigerlang");
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
    }
}
