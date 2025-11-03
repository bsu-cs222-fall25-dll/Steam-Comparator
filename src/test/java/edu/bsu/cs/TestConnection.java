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
    public void testGamesConnection() throws URISyntaxException, IOException {
        String gamesConnection = readJsonAsString(SteamConnection.connectToGames("76561198799220336"));
        Assertions.assertNotNull(gamesConnection);
    }

    @Test
    public void testUserConnection() throws IOException, URISyntaxException {
        String userConnection = readJsonAsString(SteamConnection.connectToUser("76561198799220336"));
        Assertions.assertNotNull(userConnection);
    }
}
