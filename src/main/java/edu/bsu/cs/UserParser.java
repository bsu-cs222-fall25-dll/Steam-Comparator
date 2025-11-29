package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class UserParser {
    public static User parseUserData(String jsonUserData, String jsonOwnedGames, String jsonRecentGames) throws SteamApiException {
        try {
            String steamID = parseSteamID(jsonUserData);
            String displayName = parseDisplayName(jsonUserData);
            Game userMostPlayedGame = GameParser.parseMostPlayedGame(jsonOwnedGames);
            List<Game> recentGames = GameParser.parseRecentlyPlayedGames(jsonRecentGames);
            return new User(steamID, displayName, userMostPlayedGame, recentGames);

        } catch (Exception e) {
            throw new SteamApiException("Failed to parse user data.", e);
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
