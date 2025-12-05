package edu.bsu.cs;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameListBuilder {

    public List<Game> getSortedGames(String userData, String gameData, String sortOption, int numberOfGames) throws Exception {
        if (userData == null || gameData == null) {
            return Collections.emptyList();
        }

        User user = UserParser.parseUserData(userData, gameData);

        if (user.allGames().isEmpty()) {
            return Collections.emptyList();
        }

        List<Game> gamesToDisplay;
        if ("Most hours".equals(sortOption)) {
            gamesToDisplay = SortingAlgorithm.quickSort(user.allGames(), "minutes");
        } else {
            gamesToDisplay = SortingAlgorithm.quickSort(user.allGames(), "lastPlayedTimestamp");
        }

        return gamesToDisplay.stream()
                .limit(numberOfGames)
                .collect(Collectors.toList());
    }
}
