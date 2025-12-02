package edu.bsu.cs;

import java.util.List;

public record User(String steamID, String displayName, List<Game> allGames, List<Game> recentGames) {

    public String printUser() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(displayName())
                .append("\nUser ID: ").append(steamID())
                .append("\n\nRecently Played Games (hours are from the last two weeks):\n");

        for (Game g : recentGames) {
            sb.append("- ").append(g.gameName())
                    .append(": ").append(String.format("%.1f", g.minutes() / 60.0)).append(" hours \n");
        }
        return sb.toString();
    }
}
