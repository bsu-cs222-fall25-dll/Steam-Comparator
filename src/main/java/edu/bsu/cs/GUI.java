package edu.bsu.cs;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class GUI {

    private TextField accountField;
    private TextArea outputArea;
    private Button fetchButton;
    private Button clearButton;
    private final List<User> users = new ArrayList<>();

    public void start(Stage stage) {
        stage.setTitle("Steam Comparator");

        //URL Type In
        Label accountLabel = new Label("Enter Steam Profile URL:");
        accountField = new TextField();
        accountField.setPromptText("example - https://steamcommunity.com/id/joe");

        //Buttons
        fetchButton = new Button("Get User");
        fetchButton.setOnAction(_->fetchUserData());

        clearButton = new Button("Clear List");
        clearButton.setOnAction(_->clearUsers());

        HBox buttonRow = new HBox(10, fetchButton, clearButton);

        //Output Field
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(400);

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(accountLabel, accountField, buttonRow, outputArea);

        Scene scene = new Scene(layout, 650, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void fetchUserData() {
        String input = accountField.getText().trim();
        if (input.isEmpty()){
            outputArea.setText("Please enter a Steam Profile URL");
            return;
        }

        fetchButton.setDisable(true);

        new Thread(()-> {
            try {
                String accountName;
                if (input.startsWith("http")) {
                    accountName = AccountParser.parseAccountName(input);
                } else {
                    accountName = input;
                }

                String userJson = UserFetcher.getUserDataAsString(accountName);
                String gameJson = UserFetcher.getGameDataAsString(accountName);
                User user = UserParser.parseUserData(userJson, gameJson);

                users.add(user);

                Platform.runLater(()->{
                    displayUsers();
                    fetchButton.setDisable(false);
                    accountField.clear();
                });
            } catch (Exception e) {
                Platform.runLater(()->{
                    outputArea.appendText("Error fetching user "+input+ ": " + e.getMessage()+"\n");
                    fetchButton.setDisable(false);
                });
            }
        }).start();
    }

    private void clearUsers() {
        users.clear();
        outputArea.clear();
    }

    private void displayUsers() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fetched Users: \n-------------------------\n");
        for (User user : users){
            builder.append(user.printUser()).append("\n\n");
        }
        outputArea.setText(builder.toString());
    }

    public static void main(String[] args) {
        launch();
    }
}


