package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static edu.bsu.cs.UserFetcher.readJsonAsString;

public class TestConnection {

    @Test
    public void getIdTest() throws Exception {
        String id = SteamConnection.getAccountId("tigerlang");
        Assertions.assertEquals("76561198799220336", id);
    }

    @Test
    public void testGamesConnection() throws URISyntaxException, IOException, SteamApiException {
        String gamesConnection = readJsonAsString(SteamConnection.connectToGames("76561198799220336"));
        Assertions.assertNotNull(gamesConnection);
    }

    @Test
    public void testUserConnection() throws IOException, URISyntaxException, SteamApiException {
        String userConnection = readJsonAsString(SteamConnection.connectToUser("76561198799220336"));
        Assertions.assertNotNull(userConnection);
    }
    @Test
    public void testParseAccountIdInvalidJson() {
        String invalidJson = "{\"notsteamid\":\"123\"}";
        Assertions.assertNull(AccountParser.parseAccountId(invalidJson));
    }

    @Test
    public void testParseAccountNameInvalidUrl() {
        Assertions.assertThrows(SteamApiException.class, () ->
                AccountParser.parseAccountName("https://example.com/notsteam/"));
    }

    @Test
    public void testParseAccountNameEmptyPath() {
        Assertions.assertThrows(SteamApiException.class, () ->
                AccountParser.parseAccountName("https://steamcommunity.com/"));
    }
    @Test
    public void testParseRecentlyPlayedGamesLimitFive() throws SteamApiException {
        String json = TestFormat.readFileAsString("sampleRecentlyPlayed.json");
        var games = GameParser.parseRecentlyPlayedGames(json);
        Assertions.assertTrue(games.size() <= 5);
    }

    @Test
    public void testParseRecentlyPlayedGamesThrowsOnEmpty() {
        String json = "{\"response\":{\"games\":[]}}";
        Assertions.assertThrows(SteamApiException.class, () ->
                GameParser.parseRecentlyPlayedGames(json));
    }

    @Test
    public void testParseUserDataThrowsOnInvalidJson() {
        Assertions.assertThrows(SteamApiException.class, () ->
                UserParser.parseUserData("{}", "{}","{}" ));
    }
    @Test
    public void testPrintGameFormatting() {
        Game g = new Game(135, 12345, "Test Game");
        Assertions.assertTrue(g.printGame().contains("2.25")); // 135 min = 2.25 hours
    }

}
