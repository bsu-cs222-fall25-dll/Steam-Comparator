package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class TestSort {

    @Test
    public void testGameQuickSort() throws SteamApiException {
        List<Game> sortedGames = SortingAlgorithm.quickSort(GameParser.parseAllGames(readFileAsString("sampleGamesOwned.json")),"minutes");
        Assertions.assertEquals(15885, sortedGames.get(0).minutes());
        Assertions.assertEquals(15453, sortedGames.get(1).minutes());
        Assertions.assertEquals(10933, sortedGames.get(2).minutes());
    }

    public static String readFileAsString(String inputFile){
        try (InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputFile)) {
            return new String(Objects.requireNonNull(file).readAllBytes(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
