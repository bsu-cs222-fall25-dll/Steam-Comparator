package edu.bsu.cs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
This file and other bits of code found in the SteamConnection.java at the top
were constructed using a tutorial on "Medium", while taking into the top comment
advising to put the key in a new file not the gradle.properties
 */

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("config.properties")){
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties file");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
