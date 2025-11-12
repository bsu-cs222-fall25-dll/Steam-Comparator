package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.Objects;

public class TestFormat {

    static {
        System.setProperty("com.jayway.jsonpath.cache.disable", "true");
    }

    @Test
    public void testAccess(){
        String jsonData = readFileAsString("sample.json");
        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testUrlParse() throws MalformedURLException, SteamApiException {
        String vanityID = AccountParser.parseAccountName("https://steamcommunity.com/id/tigerlang/");

        Assertions.assertEquals("tigerlang", vanityID);
    }

    @Test
    public void testSteamIDFetched() {
        String steamID = UserParser.parseSteamID(readFileAsString("sampleUserData.json"));

        Assertions.assertEquals("76561198799220336", steamID);
    }

    @Test
    public void testDisplayNameFetched() {
        String displayName = UserParser.parseDisplayName(readFileAsString("sampleUserData.json"));
        System.out.println(displayName);
        Assertions.assertEquals("Tigerlang", displayName);
    }

    @Test
    public void testGameParser() throws SteamApiException {
        Game testGame = GameParser.parseMostPlayedGame(readFileAsString("sampleGamesOwned.json"));
        Assertions.assertEquals("Game Name: Tom Clancy's Rainbow Six® Siege X\nHours played: 264.75", testGame.printGame());
    }

    @Test
    public void testUserParser() throws SteamApiException {
        User testUser = UserParser.parseUserData(readFileAsString("sampleUserData.json"), readFileAsString("sampleGamesOwned.json"));
        Assertions.assertEquals("User: Tigerlang\n" +
                        "User ID: 76561198799220336\n" +
                        "Most Played Game:\n" +
                        "Game Name: Tom Clancy's Rainbow Six® Siege X\n" +
                        "Hours played: 264.75\n" +
                        "\n" +
                        "Recently Played Games (Hours in the past 2 weeks):\n" +
                        "- Apex Legends: 15.52 hours \n" +
                        "- Megabonk: 11.45 hours \n" +
                        "- Overwatch® 2: 5.40 hours \n" +
                        "- Escape the Backrooms: 3.83 hours \n" +
                        "- Middle-earth™: Shadow of Mordor™: 2.02 hours \n",
                testUser.printUser());
    }

    @Test
    public void testIfAccountNameIsID() throws Exception {
        String accountName = "239583265620358632053682";
        String returned = SteamConnection.getAccountId(accountName);
        Assertions.assertEquals("239583265620358632053682", returned);
    }

    public static String readFileAsString(String inputFile){
        try (InputStream file = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(inputFile)) {
            return new String(Objects.requireNonNull(file).readAllBytes(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
