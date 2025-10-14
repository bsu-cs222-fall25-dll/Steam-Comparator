package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestConnection {

    @Test
    public void getIdTest() throws Exception {
        String id = SteamConnection.getAccountId();
        Assertions.assertEquals("76561198799220336", id);
    }

    @Test
    public void testAccess(){
        String jsonData = Formatter.readFileAsString("sample.json");
        Assertions.assertNotNull(jsonData);
    }

}
