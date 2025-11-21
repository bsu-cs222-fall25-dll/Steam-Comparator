package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class TestSort {

    @Test
    public void testGameSortDifferent() throws SteamApiException {
        List<Game> testParseMinutes = GameParser.parseGames(readFileAsString("sampleGamesOwned.json"), "0");
        List<Game> testParserTime = GameParser.parseGames(readFileAsString("sampleGamesOwned.json"), "1");
        Assertions.assertNotEquals(testParseMinutes,testParserTime);
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
