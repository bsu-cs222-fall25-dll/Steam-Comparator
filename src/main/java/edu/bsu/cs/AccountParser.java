package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.net.URI;
import java.net.URISyntaxException;

public class AccountParser {
    public static String parseAccountId(String json) throws SteamApiException {
        try {
            // First, check if the API call was successful
            int success = JsonPath.read(json, "$.response.success");
            if (success != 1) {
                String message = JsonPath.read(json, "$.response.message");
                throw new SteamApiException("Could not find Steam user: " + message);
            }
            // If successful, parse and return the steamid
            return JsonPath.read(json, "$.response.steamid");
        } catch (PathNotFoundException e) {
            // This will catch cases where the JSON structure is not what we expect
            throw new SteamApiException("Failed to parse Steam ID from API response.", e);
        }
    }

    public static String parseAccountName(String steamLink) throws SteamApiException {
        try {
            // Handle empty input
            if (steamLink == null || steamLink.trim().isEmpty()) {
                throw new SteamApiException("Please enter a Steam profile link or ID.");
            }
            steamLink = steamLink.trim();

            // If the input is just numbers, it's likely a direct SteamID64
            if (steamLink.matches("\\d{17}")) {
                return steamLink;
            }

            URI steamUrl = new URI(steamLink);
            checkIfSteamLink(steamUrl);

            String[] urlParts = steamUrl.getPath().split("/");

            // Find the last non-empty part of the URL path
            String name = "";
            for (int i = urlParts.length - 1; i >= 0; i--) {
                if (!urlParts[i].isEmpty()) {
                    name = urlParts[i];
                    break;
                }
            }

            if (name.isEmpty()) {
                throw new SteamApiException("Could not determine username or ID from the link.");
            }

            return name;
        } catch (URISyntaxException e) {
            // If it's not a valid URI, it might be a vanity name by itself
            if (steamLink.matches("[a-zA-Z0-9_-]+")) {
                return steamLink;
            }
            throw new SteamApiException("Invalid URL format: " + steamLink, e);
        }
    }

    public static void checkIfSteamLink(URI steamUrl) throws SteamApiException {
        String host = steamUrl.getHost();
        if (host == null || !host.contains("steamcommunity.com")) {
            throw new SteamApiException("Please enter a valid link to a Steam profile (e.g., steamcommunity.com/id/...).");
        }

        String path = steamUrl.getPath();
        if (path == null || (!path.startsWith("/id/") && !path.startsWith("/profiles/"))) {
            throw new SteamApiException("Please enter a link to a user's profile, not a community page or other link.");
        }
    }
}
