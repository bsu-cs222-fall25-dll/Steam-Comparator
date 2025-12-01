package edu.bsu.cs;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
            displayUser(inputField1, outputField1);
            displayUser(inputField2, outputField2);
        } catch (Exception ex) {
            displayError(ex.getMessage());
        }
    }

    private void displayUser(TextField inputField, TextArea outputField) throws Exception {
        String steamLink = inputField.getText().trim();
        if (steamLink.isEmpty()) {
            displayError("Nothing entered");
            return;
        }

        String accountName = AccountParser.parseAccountName(steamLink);
        String userData = UserFetcher.getUserDataAsString(accountName);
        String gameData = UserFetcher.getOwnedGamesAsString(accountName);
        String recentData = UserFetcher.getRecentlyPlayedDataAsString(accountName);
        User user = UserParser.parseUserData(userData, gameData, recentData);
        outputField.setText(user.printUser());

        if (outputField.getText().trim().isEmpty()) {
            displayError("User not found");
        }
    }

    private void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
