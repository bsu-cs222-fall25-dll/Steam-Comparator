package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.Objects;

public class TestConnection {

    @Test
    public void getIdTest() throws IOException, URISyntaxException {
        String id = SteamConnection.getAccountId();
        Assertions.assertEquals("76561198799220336", id);
    }

    @Test
    public void testAccess(){
        String jsonData = Formatter.readFileAsString("sample.json");
        Assertions.assertNotNull(jsonData);
    }
}
