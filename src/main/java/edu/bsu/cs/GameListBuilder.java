package edu.bsu.cs;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameListBuilder {

    public List<Game> getSortedGamesForUser(String steamIdentifier, String sortOption, int numberOfGames) throws Exception {
        if (steamIdentifier == null || steamIdentifier.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String accountName = AccountParser.parseAccountName(steamIdentifier);
        String userData = UserFetcher.getUserDataAsString(accountName);
        String gameData = UserFetcher.getOwnedGamesAsString(accountName);
        User user = UserParser.parseUserData(userData, gameData);

        if (user.allGames().isEmpty()) {
            return Collections.emptyList();
        }

        List<Game> gamesToDisplay;
        if ("Most hours".equals(sortOption)) {
            gamesToDisplay = SortingAlgorithm.quickSort(user.allGames(), "minutes");
            Collections.reverse(gamesToDisplay);
        } else {
            gamesToDisplay = SortingAlgorithm.quickSort(user.allGames(), "lastPlayedTimestamp");
            Collections.reverse(gamesToDisplay);
        }

        return gamesToDisplay.stream()
                .limit(numberOfGames)
                .collect(Collectors.toList());
    }
}
