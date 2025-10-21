package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class UserParser {
    public static User parseUserData(String jsonData) {
        String steamID = JsonPath.read(jsonData, "$.response.players.*.steamid");
        String displayName = JsonPath.read(jsonData, "$.response.players.*.personaname");

        return new User(steamID, displayName);
    }

    public static String parseSteamID(String jsonData) {
        return JsonPath.read(jsonData, "$.response.players.*.steamid");
    }

    public static String parseDisplayName(String jsonData) {
        return JsonPath.read(jsonData, "$.response.players.*.personaname");
    }
}
