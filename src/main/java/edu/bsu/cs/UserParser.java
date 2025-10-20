package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

public class UserParser {
    public static User parseUserData(String jsonData) {
        String steamID = JsonPath.read(jsonData, "$.response.players.*.steamid");
        String displayName = JsonPath.read(jsonData, "$.response.players.*.personaname");

        return new User(steamID, displayName);
    }
}
