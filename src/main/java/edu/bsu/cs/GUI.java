package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private TextField inputField1;
    private TextArea outputField1;
    private TextField inputField2;
    private TextArea outputField2;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SteamComparator");
        Pane root = createRoot();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private Pane createRoot() {
        inputField1 = new TextField();
        outputField1 = new TextArea();
        outputField1.setEditable(false);

        VBox leftColumn = new VBox(5);
        leftColumn.getChildren().addAll(
                new Label("Steam Link:"), inputField1,
                new Label("Information:"), outputField1
        );
        
        inputField2 = new TextField();
        outputField2 = new TextArea();
        outputField2.setEditable(false);

        VBox rightColumn = new VBox(5);
        rightColumn.getChildren().addAll(
                new Label("Steam Link:"), inputField2,
                new Label("Information:"), outputField2
        );

        return getHBox(leftColumn, rightColumn);

    }

    private HBox getHBox(VBox leftColumn, VBox rightColumn) {
        Button enterAllButton = new Button("Enter All");
        enterAllButton.setDefaultButton(true);
        enterAllButton.setOnAction(e -> {
            try {
                displayUser(inputField1, outputField1);
                displayUser(inputField2, outputField2);
            } catch (Exception ex) {
                displayError(ex.getMessage());
            }
        });

        VBox buttonBox = new VBox(enterAllButton);
        HBox root = new HBox(10, leftColumn, rightColumn, buttonBox);
        root.setPrefSize(900, 400);
        return root;
    }

    private void displayUser(TextField inputField, TextArea outputField) throws Exception {
        String steamLink =  inputField.getText().trim();
        if (steamLink.isEmpty()){
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

    static void displayError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
