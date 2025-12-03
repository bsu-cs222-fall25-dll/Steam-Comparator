package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import net.minidev.json.JSONArray;


import java.util.List;

public class UserParser {
    public static User parseUserData(String jsonUserData, String jsonOwnedGames) throws SteamApiException {
        try {
            // Check if the player array is empty. This happens with invalid SteamIDs.
            JSONArray players = JsonPath.read(jsonUserData, "$.response.players");
            if (players.isEmpty()) {
                throw new SteamApiException("No Steam user found with the given ID. Please check the ID and try again.");
            }

            String steamID = parseSteamID(jsonUserData);
            String displayName = parseDisplayName(jsonUserData);
            List<Game> allGames = GameParser.parseAllGames(jsonOwnedGames);
            return new User(steamID, displayName, allGames);

        } catch (PathNotFoundException e) {
            // This can happen if the overall JSON structure is wrong
            throw new SteamApiException("Failed to parse user data: The API response was not in the expected format.", e);
        } catch (Exception e) {
            // Catch any other unexpected errors during parsing
            if (e instanceof SteamApiException) {
                throw e; // Re-throw our specific exceptions
            }
            throw new SteamApiException("An unexpected error occurred while parsing user data.", e);
        }
    }

    public static String parseSteamID(String jsonUserData) {
        try {
            return JsonPath.read(jsonUserData, "$.response.players[0].steamid");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse SteamID" + e.getMessage(), e);
        }
    }

    public static String parseDisplayName(String jsonUserData) {
        try {
            return JsonPath.read(jsonUserData, "$.response.players[0].personaname");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse display name" + e.getMessage(), e);
        }
    }
}
