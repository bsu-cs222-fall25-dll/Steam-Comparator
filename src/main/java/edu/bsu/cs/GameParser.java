package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.List;
import java.util.Map;

public class GameParser {
    public static Game parseMostPlayedGame(String jsonGameData) throws SteamApiException {
        try{
            ReadContext context = JsonPath.parse(jsonGameData);

            List<Map<String, Object>> games = context.read("$.response.games[*]");

            if (games == null || games.isEmpty()){
                throw new RuntimeException("No games found in the JSON data.");
            }

            Map<String, Object> mostPlayed = null;
            int highestMinutes = -1;

            for (Map<String, Object> game : games){
                int playtime = ((Number) game.get("playtime_forever")).intValue();
                if (playtime > highestMinutes) {
                    highestMinutes = playtime;
                    mostPlayed = game;
                }
            }

            if (mostPlayed != null){
                String gameName = (String) mostPlayed.get("name");
                int appID = ((Number) mostPlayed.get("appid")).intValue();
                return new Game(highestMinutes, appID, gameName);
            } else {
                throw new RuntimeException("Couldn't parse for most played game.");
            }
        } catch (Exception e) {
            throw new SteamApiException("Error parsing game data.", e);
        }

    }
}
