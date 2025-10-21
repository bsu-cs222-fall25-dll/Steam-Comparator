package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


public class TestConnection {

    @Test
    public void getIdTest() throws Exception {
        String id = SteamConnection.getAccountId("tigerlang");
        Assertions.assertEquals("76561198799220336", id);
    }

    @Test
    public void testAccess(){
        String jsonData = Formatter.readFileAsString("sample.json");
        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testID() throws IOException, URISyntaxException {
        String jsonData = Formatter.readFileAsString("sampleUserData.json");
        String userData = Formatter.readJsonAsString(SteamConnection.connectToUser("76561198799220336"));

        Assertions.assertEquals(jsonData, userData);
    }

    @Test
    public void testUrlParse() throws MalformedURLException {
        String vanityID = AccountParser.parseAccountName("https://steamcommunity.com/id/tigerlang/");

        Assertions.assertEquals("tigerlang", vanityID);

    }
}
