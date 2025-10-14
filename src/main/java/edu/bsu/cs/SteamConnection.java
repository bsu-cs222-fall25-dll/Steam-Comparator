package edu.bsu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class SteamConnection {

    public static String getAccountId() throws IOException, URISyntaxException {
        String encodedUrlString = "https://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=F2B3A13F8246165E1FD566131CB5A81F&vanityurl=tigerlang" +
                URLEncoder.encode(String.valueOf(Charset.defaultCharset())) +
                "&response=steamid";
        URI uri = new URI(encodedUrlString);
        URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FinalProject/0.1 (caleb.langley@bsu.edu)");
        connection.connect();
        return encodedUrlString;
    }

}
