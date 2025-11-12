package edu.bsu.cs;

import java.util.List;

public record User(String steamID, String displayName, Game mostPlayed, List<Game> recentGames) {

    public String printUser() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(displayName())
                .append("\nUser ID: ").append(steamID())
                .append("\nMost Played Game:\n")
                .append(mostPlayed.printGame())
                .append("\n\nRecently Played Games (Hours in the past 2 weeks):\n");

        for (Game g : recentGames) {
            sb.append("- ").append(g.gameName())
                    .append(": ").append(String.format("%.2f", g.minutes() / 60.0)).append(" hours \n");
        }
        return sb.toString();
    }
}
