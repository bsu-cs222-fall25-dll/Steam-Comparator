package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class TestFormat {
    @Test
    public void testAccess(){
        String jsonData = Formatter.readFileAsString("sample.json");
        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testUrlParse() throws MalformedURLException {
        String vanityID = AccountParser.parseAccountName("https://steamcommunity.com/id/tigerlang/");

        Assertions.assertEquals("tigerlang", vanityID);
    }

    @Test
    public void testSteamIDFetched() {
        String steamID = UserParser.parseSteamID(Formatter.readFileAsString("sampleUserData.json"));

        Assertions.assertEquals("76561198799220336", steamID);
    }

    @Test
    public void testDisplayNameFetched() {
        String displayName = UserParser.parseDisplayName(Formatter.readFileAsString("sampleUserData.json"));

        Assertions.assertEquals("Tigerlang", displayName);
    }
}
