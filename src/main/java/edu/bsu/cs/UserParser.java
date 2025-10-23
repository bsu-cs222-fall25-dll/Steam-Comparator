package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

public class UserParser {
    public static User parseUserData(String jsonData) {
        String steamID = parseSteamID(jsonData);
        String displayName = parseDisplayName(jsonData);

        return new User(steamID, displayName);
    }

    public static String parseSteamID(String jsonData) {
        try {
            return JsonPath.read(jsonData, "$.response.players[0].steamid");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse SteamID" + e.getMessage(), e);
        }
    }

    public static String parseDisplayName(String jsonData) {
        try {
            return JsonPath.read(jsonData, "$.response.players[0].personaname");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse display name" + e.getMessage(), e);
        }
    }
}
