package edu.bsu.cs;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class GUIController {

    @FXML
    private TextField inputField1;
    @FXML
    private ListView<Game> gameListView1;
    @FXML
    private TextField inputField2;
    @FXML
    private ListView<Game> gameListView2;
    @FXML
    private ComboBox<String> compareOptions;
    @FXML
    private ComboBox<Integer> amountOfGames;

    private final GameListBuilder profileService = new GameListBuilder();

    @FXML
    public void initialize() {
        compareOptions.getItems().addAll("Most hours", "Recently Played");
        compareOptions.setValue("Most hours");
        amountOfGames.getItems().addAll(5, 10, 25, 50);
        amountOfGames.setValue(10);

        gameListView1.setCellFactory(param -> new GameListCell());
        gameListView2.setCellFactory(param -> new GameListCell());
    }

    @FXML
    private void handleCompareAction() {
        String sortOption = compareOptions.getValue();
        int numberOfGames = amountOfGames.getValue();

        gameListView1.getItems().clear();
        gameListView2.getItems().clear();

        displayUser(inputField1, gameListView1, sortOption, numberOfGames);
        displayUser(inputField2, gameListView2, sortOption, numberOfGames);
    }

    private void displayUser(TextField inputField, ListView<Game> gameListView, String sortOption, int numberOfGames) {
        try {
            String steamIdentifier = inputField.getText();
            List<Game> games = profileService.getSortedGamesForUser(steamIdentifier, sortOption, numberOfGames);

            if (games.isEmpty() && steamIdentifier != null && !steamIdentifier.trim().isEmpty()) {
                gameListView.setPlaceholder(new Label("Profile is private or has no game data."));
            } else {
                gameListView.setItems(FXCollections.observableArrayList(games));
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data Fetch Error");
            alert.setHeaderText("Could not retrieve user data.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
