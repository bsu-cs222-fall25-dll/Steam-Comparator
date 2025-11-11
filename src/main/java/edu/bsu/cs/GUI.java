package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class GUI extends Application {
    static void main(String[] args) {
        launch(args);
    }

    private TextField inputField = new TextField();
    private TextArea outputField = new TextArea();


    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Label("SteamComparator"));
        primaryStage.setScene(scene);
        primaryStage.show();
        outputField.setEditable(false);
        configure(primaryStage);
        configureInputField();
    }

    private void configure(Stage stage){
        stage.setTitle("SteamComparator");
        stage.setScene(new Scene(createRoot()));
        stage.sizeToScene();
        stage.show();
    }
    private void configureInputField() {
        inputField.setOnAction((ActionEvent) ->  {
            try {
                displayUser();
            } catch (IOException | URISyntaxException e) {
                displayError("Network Connection Issue");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Pane createRoot() {
        VBox leftColumn = new VBox(5);
        TextField inputField1 = new TextField();
        TextArea outputField1 = new TextArea();
        outputField1.setEditable(false);
        leftColumn.getChildren().addAll(
                new Label("Steam Link:"), inputField1,
                new Label("Information:"), outputField1
        );

        VBox rightColumn = new VBox(5);
        TextField inputField2 = new TextField();
        TextArea outputField2 = new TextArea();
        outputField2.setEditable(false);
        rightColumn.getChildren().addAll(
                new Label("Steam Link:"), inputField2,
                new Label("Information:"), outputField2
        );

        HBox root = new HBox(10);
        root.getChildren().addAll(leftColumn, rightColumn);

        root.setPrefSize(800, 400);

        this.inputField = inputField1;
        this.outputField = outputField1;

        this.inputField = inputField2;
        this.outputField = outputField2;

        return root;
    }

    private HBox getHBox(VBox leftColumn, VBox rightColumn) {
        Button enterAllButton = new Button("Enter All");
        enterAllButton.setDefaultButton(true);
        enterAllButton.setOnAction(e -> {
            try {
                displayUser(inputField1, outputField1);
                displayUser(inputField2, outputField2);
            } catch (Exception ex) {
                displayError("Error processing input: " + ex.getMessage());
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
        }
        String accountName = AccountParser.parseAccountName(steamLink);
        String userData = UserFetcher.getUserDataAsString(accountName);
        String gameData = UserFetcher.getGameDataAsString(accountName);
        User user = UserParser.parseUserData(userData, gameData);
        outputField.setText(user.printUser());
        String output = outputField.getText().trim();
        if(output.isEmpty()){
            displayError("No article found");
        }
    }

    static void displayError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
