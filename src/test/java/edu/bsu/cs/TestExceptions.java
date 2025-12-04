package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExceptions {
    @Test
    public void testExceptionMessage(){
        String message = "test";
        SteamApiException exception = new SteamApiException(message);
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void testExceptionCause(){
        String message = "test message";
        Throwable cause = new RuntimeException("test cause");
        SteamApiException exception = new SteamApiException(message, cause);
        Assertions.assertEquals(message, exception.getMessage());
        Assertions.assertEquals(cause, exception.getCause());
    }
}
