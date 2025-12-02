package edu.bsu.cs;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Collections;
import java.util.List;

public class GUIController {

    @FXML
    private TextField inputField1;
    @FXML
    private TextArea outputField1;
    @FXML
    private TextField inputField2;
    @FXML
    private TextArea outputField2;
    @FXML
    private ComboBox<String> compareOptions;
    @FXML
    private ComboBox<Integer> amountOfGames;

    @FXML
    public void initialize() {
        compareOptions.getItems().addAll("Most hours", "Recent hours");
        compareOptions.setValue("Most hours");

        amountOfGames.getItems().addAll(5, 10, 25, 50);
        amountOfGames.setValue(10);
    }

    @FXML
    private void handleCompareAction() {
        try {
            String sortOption = compareOptions.getValue();
            int numberOfGames = amountOfGames.getValue();

            outputField1.clear();
            outputField2.clear();

            displayUser(inputField1, outputField1, sortOption, numberOfGames);
            displayUser(inputField2, outputField2, sortOption, numberOfGames);
        } catch (Exception ex) {
            displayError(ex.getMessage());
        }
    }

    private void displayUser(TextField inputField, TextArea outputField, String sortOption, int numberOfGames) throws Exception {
        String steamIdentifier = inputField.getText();
        if (steamIdentifier == null || steamIdentifier.trim().isEmpty()) {
            // Don't show an error if the field is just empty.
            return;
        }

        String accountName = AccountParser.parseAccountName(steamIdentifier);
        String userData = UserFetcher.getUserDataAsString(accountName);
        String gameData = UserFetcher.getOwnedGamesAsString(accountName);
        String recentData = UserFetcher.getRecentlyPlayedDataAsString(accountName);
        User user = UserParser.parseUserData(userData, gameData, recentData);

        if (user.allGames().isEmpty() && user.recentGames().isEmpty()) {
            String privateProfileMessage = "User: " + user.displayName() +
                                           "\nUser ID: " + user.steamID() +
                                           "\n\nThis profile is private or has no game data available.";
            outputField.setText(privateProfileMessage);
            return;
        }

        List<Game> gamesToDisplay;
        if ("Most hours".equals(sortOption)) {
            // Sort all games by total playtime (descending)
            gamesToDisplay = SortingAlgorithm.quickSort(user.allGames(), "minutes");
            Collections.reverse(gamesToDisplay); // Show most played first
        } else {
            // The recent games list from the API is already sorted by recency.
            gamesToDisplay = user.recentGames();
        }

        outputField.setText(formatUserOutput(user, gamesToDisplay, numberOfGames, sortOption));
    }

    private String formatUserOutput(User user, List<Game> games, int numberOfGames, String sortOption) {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(user.displayName())
                .append("\nUser ID: ").append(user.steamID())
                .append("\n\n");

        if (games.isEmpty()) {
            if ("Recent hours".equals(sortOption)) {
                sb.append("No recently played games in the last two weeks.");
            } else {
                sb.append("No games with playtime found for this user.");
            }
            return sb.toString();
        }

        String title = "Recent hours".equals(sortOption) ? "Recently Played Games:" : "Most Played Games:";
        sb.append(title).append("\n");

        int gamesToShow = Math.min(numberOfGames, games.size());
        for (int i = 0; i < gamesToShow; i++) {
            Game g = games.get(i);
            // The 'minutes' field for recent games is playtime in the last 2 weeks.
            double hours = g.minutes() / 60.0;
            sb.append(String.format("- %s: %.1f hours\n", g.gameName(), hours));
        }
        return sb.toString();
    }

    private void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
