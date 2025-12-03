package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import net.minidev.json.JSONArray;


import java.util.List;

public class UserParser {
    public static User parseUserData(String jsonUserData, String jsonOwnedGames) throws SteamApiException {
        try {
            JSONArray players = JsonPath.read(jsonUserData, "$.response.players");
            if (players.isEmpty()) {
                throw new SteamApiException("No Steam user found with the given ID. Please check the ID and try again.");
            }

            String steamID = parseSteamID(jsonUserData);
            String displayName = parseDisplayName(jsonUserData);
            List<Game> allGames = GameParser.parseAllGames(jsonOwnedGames);
            return new User(steamID, displayName, allGames);

        } catch (PathNotFoundException e) {
            throw new SteamApiException("Failed to parse user data: The API response was not in the expected format.", e);
        } catch (Exception e) {
            if (e instanceof SteamApiException) {
                throw e;
            }
            throw new SteamApiException("An unexpected error occurred while parsing user data.", e);
        }
    }

    public static String parseUserInfo(String jsonUserData, String infoType) {
        try{
            return JsonPath.read(jsonUserData, "$.response.players[0]." + infoType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse user info" + e.getMessage());
        }
    }

    public static String parseSteamID(String jsonUserData) {
            return parseUserInfo(jsonUserData, "steamid");
    }

    public static String parseDisplayName(String jsonUserData) {
            return parseUserInfo(jsonUserData, "personaname");
    }
}
