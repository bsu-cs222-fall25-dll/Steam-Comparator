package edu.bsu.cs;

import java.util.List;

public record User(String steamID, String displayName, List<Game> allGames) {
}
