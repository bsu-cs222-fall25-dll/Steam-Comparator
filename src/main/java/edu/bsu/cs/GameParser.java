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

    private static List<Game> storeGamesInList(List<Map<String, Object>> allGames) {
        if (allGames == null) {
            return new ArrayList<>();
        }
        List<Game> games = new ArrayList<>();
        for (Map<String, Object> game : allGames) {
            String name = (String) game.get("name");
            int appID = ((Number) game.get("appid")).intValue();
            int minutes = ((Number) game.get("playtime_forever")).intValue();
            // rtime_last_played is a Unix timestamp (long)
            long lastPlayedTimestamp = game.containsKey("rtime_last_played") ? ((Number) game.get("rtime_last_played")).longValue() : 0;
            games.add(new Game(minutes, appID, lastPlayedTimestamp, name));
        }
        return games;
    }
}
