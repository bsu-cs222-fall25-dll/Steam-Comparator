package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class TestSort {



    public static String readFileAsString(String inputFile){
        try (InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputFile)) {
            return new String(Objects.requireNonNull(file).readAllBytes(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
