package edu.bsu.cs;

import java.net.MalformedURLException;
import java.net.URL;

public class AccountParser
{
    public static String parseAccountId(String json) {
        String key = "\"steamid\":\"";
        int start = json.indexOf(key);
        if (start == -1) return null;
        start += key.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return null;
        return json.substring(start, end);
    }

    public static String parseAccountName(String steamLink) throws MalformedURLException {
        URL steamUrl;
        try {
           steamUrl = new URL(steamLink);
       } catch (MalformedURLException e) {
            return "0";

       }
        String[] urlParts = steamUrl.getPath().split("/");
        return urlParts[urlParts.length - 1].isEmpty() ? urlParts[urlParts.length - 2] : urlParts[urlParts.length - 1];
    }

}
