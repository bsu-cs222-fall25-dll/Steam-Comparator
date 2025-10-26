package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

public class UserParser {
    public static User parseUserData(String jsonUserData, String jsonGameData) {
        String steamID = parseSteamID(jsonUserData);
        String displayName = parseDisplayName(jsonUserData);
        Game userMostPlayedGame = GameParser.parseMostPlayedGame(jsonGameData);

        return new User(steamID, displayName, userMostPlayedGame);
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
