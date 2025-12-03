package edu.bsu.cs;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

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

    @FXML
    public void initialize() {
        compareOptions.getItems().addAll("Most hours", "Recently Played");
        compareOptions.setValue("Most hours");
        amountOfGames.getItems().addAll(5, 10, 25, 50);
        amountOfGames.setValue(10);

        setupGameListView(gameListView1);
        setupGameListView(gameListView2);
    }

    private void setupGameListView(ListView<Game> listView) {
        listView.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();
            private final Label numberLabel = new Label();
            private final Label textLabel = new Label();
            private final VBox textVBox = new VBox(textLabel);
            private final HBox hbox = new HBox(10, numberLabel, imageView, textVBox);

            @Override
            protected void updateItem(Game game, boolean empty) {
                super.updateItem(game, empty);
                if (empty || game == null) {
                    setGraphic(null);
                } else {
                    numberLabel.setText(String.format("%d.", getIndex() + 1));
                    numberLabel.setMinWidth(20);
                    numberLabel.setAlignment(Pos.CENTER_LEFT);

                    if (game.imageURL() != null && !game.imageURL().isEmpty()) {
                        String imageUrl = String.format("http://media.steampowered.com/steamcommunity/public/images/apps/%d/%s.jpg",
                                game.appID(), game.imageURL());
                        imageView.setImage(new Image(imageUrl, true));
                        imageView.setFitWidth(32);
                        imageView.setFitHeight(32);
                    } else {
                        imageView.setImage(null);
                    }

                    double hours = game.minutes() / 60.0;
                    textLabel.setText(String.format("%s\n%.1f hours", game.gameName(), hours));

                    setGraphic(hbox);
                }
            }
        });
    }

    @FXML
    private void handleCompareAction() {
        try {
            String sortOption = compareOptions.getValue();
            int numberOfGames = amountOfGames.getValue();

            gameListView1.getItems().clear();
            gameListView2.getItems().clear();

            displayUser(inputField1, gameListView1, sortOption, numberOfGames);
            displayUser(inputField2, gameListView2, sortOption, numberOfGames);
        } catch (SteamApiException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Error");
            alert.setHeaderText("An unexpected error occurred.");
            alert.setContentText("Please check the console for more details.");
            alert.showAndWait();
        }
    }

    private void displayUser(TextField inputField, ListView<Game> gameListView, String sortOption, int numberOfGames) throws Exception {
        String steamIdentifier = inputField.getText();
        if (steamIdentifier == null || steamIdentifier.trim().isEmpty()) {
            return;
        }

        String accountName = AccountParser.parseAccountName(steamIdentifier);
        String userData = UserFetcher.getUserDataAsString(accountName);
        String gameData = UserFetcher.getOwnedGamesAsString(accountName);
        User user = UserParser.parseUserData(userData, gameData);

        if (user.allGames().isEmpty()) {
            Label placeholder = new Label("This profile is private or has no game data.");
            gameListView.setPlaceholder(placeholder);
            return;
        }

        List<Game> gamesToDisplay;
        if ("Most hours".equals(sortOption)) {
            gamesToDisplay = SortingAlgorithm.quickSort(user.allGames(), "minutes");
        } else {
            gamesToDisplay = SortingAlgorithm.quickSort(user.allGames(), "lastPlayedTimestamp");
        }

        List<Game> limitedGames = gamesToDisplay.stream().limit(numberOfGames).collect(Collectors.toList());

        gameListView.setItems(FXCollections.observableArrayList(limitedGames));
    }
}
