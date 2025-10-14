package edu.bsu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class SteamConnection {

    public static URLConnection connectToSteam() throws IOException, URISyntaxException {
        String encodedUrlString = "https://steamcommunity.com/id/tigerlang/?xml=1" +
                URLEncoder.encode(String.valueOf(Charset.defaultCharset())) +
                "&rvprop=profile" + URLEncoder.encode("|",Charset.defaultCharset()) + "user&rvlimit=15&redirects";
        URI uri = new URI(encodedUrlString);
        URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FinalProject/0.1 (caleb.langley@bsu.edu)");
        connection.connect();
        return connection;
    }

}
