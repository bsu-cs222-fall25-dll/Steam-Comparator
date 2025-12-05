package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GameListBuilderTest {

    @Test
    public void testByMostHours() throws Exception {
        GameListBuilder builder = new GameListBuilder();
        String sampleUserData = TestSort.readFileAsString("sampleUserData.json");
        String sampleGameData = TestSort.readFileAsString("sampleGamesOwned.json");

        List<Game> games = builder.getSortedGames(sampleUserData, sampleGameData, "Most hours", 10);

        Assertions.assertEquals(10, games.size());
        Assertions.assertEquals("Rust", games.get(3).gameName());
    }

    @Test
    public void testByRecentHours() throws Exception {
        GameListBuilder builder = new GameListBuilder();
        String sampleUserData = TestSort.readFileAsString("sampleUserData.json");
        String sampleGameData = TestSort.readFileAsString("sampleGamesOwned.json");

        List<Game> games = builder.getSortedGames(sampleUserData, sampleGameData, "Recently Played", 50);

        Assertions.assertEquals(50, games.size());
        Assertions.assertEquals("Lethal Company", games.get(47).gameName());
    }
}
