package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;


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

}
