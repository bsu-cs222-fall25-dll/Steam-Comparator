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
    public void testGamesConnection() throws URISyntaxException, IOException {
        String gamesConnection = Formatter.readJsonAsString(SteamConnection.connectToGames("76561198799220336"));
        Assertions.assertNotNull(gamesConnection);
    }
}
