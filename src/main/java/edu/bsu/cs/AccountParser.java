package edu.bsu.cs;

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
}
