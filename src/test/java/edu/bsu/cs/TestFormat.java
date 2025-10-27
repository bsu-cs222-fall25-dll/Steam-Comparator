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
    public void testUrlParse() throws MalformedURLException {
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

    public static String readFileAsString(String inputFile){
        try (InputStream file = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(inputFile)) {
            return new String(Objects.requireNonNull(file).readAllBytes(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
