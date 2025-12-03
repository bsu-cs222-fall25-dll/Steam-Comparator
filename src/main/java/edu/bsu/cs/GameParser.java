package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameParser {

    public static List<Game> parseAllGames(String jsonGameData) throws SteamApiException {
        try {
            List<Map<String, Object>> games = JsonPath.read(jsonGameData, "$.response.games[*]");
            return storeGamesInList(games);
        } catch (PathNotFoundException e) {
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
            int lastPlayedTimestamp;
            if (game.containsKey("rtime_last_played")) {
                lastPlayedTimestamp = ((Number) game.get("rtime_last_played")).intValue();
            } else {
                lastPlayedTimestamp = 0;
            }
            // Use a safe default for the image URL
            String imageURL = game.containsKey("img_icon_url") ? (String) game.get("img_icon_url") : "";
            
            games.add(new Game(minutes, appID, lastPlayedTimestamp, name, imageURL));
        }
        return games;
    }
}
