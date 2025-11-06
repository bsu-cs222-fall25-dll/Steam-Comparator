package edu.bsu.cs;
import java.net.URI;
import java.net.URISyntaxException;

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
            URI steamUrl = new URI(steamLink);

            checkIfSteamLink(steamUrl);

            String[] urlParts = steamUrl.getPath().split("/");
            if (urlParts.length < 2) {
                throw new SteamApiException("Please enter an account link.\n");
            }

            String name = urlParts[urlParts.length - 1].isEmpty() ? urlParts[urlParts.length - 2] : urlParts[urlParts.length - 1];
            if (name.isEmpty()) {
                throw new SteamApiException("Invalid link\n");
            }

            return name;
        } catch (URISyntaxException e) {
            throw new SteamApiException("Invalid URL format. \n", e);
        }
    }

    public static void checkIfSteamLink(URI steamUrl) throws SteamApiException {
        if (!steamUrl.getHost().contains("steamcommunity.com")) {
            throw new SteamApiException("Please enter a Steam link.\n");
        }

        String[] urlParts = steamUrl.getPath().split("/");
        if (urlParts.length < 2) {
            throw new SteamApiException("Please enter an account link.\n");
        }
    }
}
