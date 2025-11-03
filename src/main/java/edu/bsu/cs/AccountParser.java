package edu.bsu.cs;

import java.net.MalformedURLException;
import java.net.URL;

public class AccountParser
{
    public static String parseAccountId(String json) {
        String key = "\"steamid\":\"";
        int start = json.indexOf(key);
        if (start == -1) return null;
        start += key.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return null;
        return json.substring(start, end);
    }

    public static String parseAccountName(String steamLink) throws SteamApiException {
        try {
            URL steamUrl = new URL(steamLink);

            if (!steamUrl.getHost().contains("steamcommunity.com")) {
                throw new SteamApiException("Account not found.\n");
            }

            String[] urlParts = steamUrl.getPath().split("/");
            if (urlParts.length < 2) {
                throw new SteamApiException("Account not found\n");
            }

            String name = urlParts[urlParts.length - 1].isEmpty() ? urlParts[urlParts.length - 2] : urlParts[urlParts.length - 1];
            if (name.isEmpty()) {
                throw new SteamApiException("Account not found.\n");
            }

            return name;
        } catch (MalformedURLException e) {
            throw new SteamApiException("Invalid URL format.", e);
        }
    }

}
