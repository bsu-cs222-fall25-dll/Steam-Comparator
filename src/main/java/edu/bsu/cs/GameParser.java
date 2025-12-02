package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.ArrayList;
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
    public static List<Game> parseRecentlyPlayedGames(String jsonRecentlyPlayedData) throws SteamApiException {
        try {
            ReadContext context = JsonPath.parse(jsonRecentlyPlayedData);
            List<Map<String, Object>> games = context.read("$.response.games[*]");

            if (games == null || games.isEmpty()) {
                throw new RuntimeException("No recent games found.");
            }

            List<Game> recentGames = new ArrayList<>();

            for (Map<String, Object> game : games) {
                String name = (String) game.get("name");
                int appID = ((Number) game.get("appid")).intValue();
                int minutes = ((Number) game.get("playtime_2weeks")).intValue();
                recentGames.add(new Game(minutes, appID, name));
            }

            return recentGames.stream().toList();

        } catch (Exception e) {
            throw new SteamApiException("Error parsing recent games data.", e);
        }
    }
    public static List<Game> parseGames(String jsonGameData, String boxValue) throws SteamApiException {
        String timePlayed = checkMostOrRecentHours(boxValue);

        try {
            ReadContext context = JsonPath.parse(jsonGameData);
            List<Map<String, Object>> allGames = context.read("$.response.games[*]");

            List<Game> games = storeGamesInList(allGames);

            return SortingAlgorithm.quickSort(games, timePlayed);
        } catch (Exception e) {
            throw new SteamApiException("Error parsing recent games data.", e);
        }
    }

    public static String checkMostOrRecentHours(String value) {
        if (value.equals("0")) {
            return "minutes";
        } else {
            return "rTime";
        }
    }

    public static List<Game> storeGamesInList(List<Map<String, Object>> allGames) {
        List<Game> games = new ArrayList<>();

        for (Map<String, Object> game: allGames) {
            String name = (String) game.get("name");
            int appID = ((Number) game.get("appid")).intValue();
            int minutes = ((Number) game.get("playtime_forever")).intValue();
            int rTime = ((Number) game.get("rtime_last_played")).intValue();
            games.add(new Game(minutes, appID,rTime, name));
        }

        return games;
    }
}

