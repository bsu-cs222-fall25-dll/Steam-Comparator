package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestParse {


    @Test
    public void testUserParser() throws SteamApiException {
        String userData = TestSort.readFileAsString("sampleUserData.json");
        String gameData = TestSort.readFileAsString("sampleGamesOwned.json");

        User user = UserParser.parseUserData(userData, gameData);
        Assertions.assertEquals("76561198799220336", user.steamID());
        Assertions.assertEquals("Tigerlang", user.displayName());
        Assertions.assertEquals("Portal", user.allGames().getFirst().gameName());
    }

    @Test
    public void testUrlParse() throws SteamApiException {
        String vanityID = AccountParser.parseAccountName("https://steamcommunity.com/id/tigerlang/");

        Assertions.assertEquals("tigerlang", vanityID);
    }
    @Test
    public void testParseAccountIdInvalidJson() {
        String invalidJson = "{\"notsteamid\":\"123\"}";
        Assertions.assertNull(AccountParser.parseAccountId(invalidJson));
    }


}
