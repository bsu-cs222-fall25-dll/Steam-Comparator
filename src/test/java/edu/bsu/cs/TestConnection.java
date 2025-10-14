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
        String id = String.valueOf(SteamConnection.connectToSteam());
        Assertions.assertEquals(id,"76561198799220336");
    }

    @Test
    public void testAccess(){
        String xmlData = Formatter.readFileAsString("sample.xml");
        Assertions.assertNotNull(xmlData);
    }
}
