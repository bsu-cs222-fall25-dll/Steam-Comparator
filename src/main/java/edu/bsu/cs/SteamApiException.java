package edu.bsu.cs;
/*
 Copilot Recommended this class to help with exception management.
 */
public class SteamApiException extends Exception {
    public SteamApiException(String message) {
        super(message);
    }

    public SteamApiException(String message, Throwable cause) {
        super(message, cause);
    }
}