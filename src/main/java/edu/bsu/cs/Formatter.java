package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Formatter {


    public static String readJsonAsString(URLConnection connection) throws IOException {
        try (InputStream input = connection.getInputStream()) {
            return new String(input.readAllBytes(), Charset.defaultCharset());
        }
    }
}
