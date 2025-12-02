package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameParser {

    public static List<Game> parseAllGames(String jsonGameData) throws SteamApiException {
        try {
            ReadContext context = JsonPath.parse(jsonGameData);
            // This path can be missing if the profile is private or has no games.
            List<Map<String, Object>> games = context.read("$.response.games[*]");
            return storeGamesInList(games);
        } catch (PathNotFoundException e) {
            // This is an expected case for private profiles, return an empty list.
            return new ArrayList<>();
        } catch (Exception e) {
            throw new SteamApiException("Error parsing all games data.", e);
        }
    }

    public static List<Game> parseRecentlyPlayedGames(String jsonRecentlyPlayedData) throws SteamApiException {
        try {
            ReadContext context = JsonPath.parse(jsonRecentlyPlayedData);
            // The 'games' array may not exist if there are no recent games.
            List<Map<String, Object>> games = context.read("$.response.games[*]");

            if (games == null || games.isEmpty()) {
                return new ArrayList<>();
            }

            List<Game> recentGames = new ArrayList<>();
            for (Map<String, Object> game : games) {
                String name = (String) game.get("name");
                int appID = ((Number) game.get("appid")).intValue();
                // Use "playtime_2weeks" for the minutes value in the context of recent games
                int minutes = ((Number) game.get("playtime_2weeks")).intValue();
                // The recent games API doesn't provide rtime_last_played, so we use 0 as a placeholder.
                // The list is already sorted by recency from the API.
                recentGames.add(new Game(minutes, appID, 0, name));
            }
            return recentGames;
        } catch (PathNotFoundException e) {
            // This is expected if the user has no recently played games.
            return new ArrayList<>();
        } catch (Exception e) {
            throw new SteamApiException("Error parsing recent games data.", e);
        }
    }

    private static List<Game> storeGamesInList(List<Map<String, Object>> allGames) {
        if (allGames == null) {
            return new ArrayList<>();
        }
        List<Game> games = new ArrayList<>();
        for (Map<String, Object> game : allGames) {
            String name = (String) game.get("name");
            int appID = ((Number) game.get("appid")).intValue();
            int minutes = ((Number) game.get("playtime_forever")).intValue();
            // rtime_last_played is not always present, so default to 0
            int rTime = game.containsKey("rtime_last_played") ? ((Number) game.get("rtime_last_played")).intValue() : 0;
            games.add(new Game(minutes, appID, rTime, name));
        }
        return games;
    }
}
