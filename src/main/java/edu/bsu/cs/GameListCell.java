package edu.bsu.cs;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameListCell extends ListCell<Game> {

    private final ImageView imageView = new ImageView();
    private final Label numberLabel = new Label();
    private final Label textLabel = new Label();
    private final VBox textVBox = new VBox(textLabel);
    private final HBox hbox = new HBox(10, numberLabel, imageView, textVBox);

    public GameListCell() {
        numberLabel.setMinWidth(20);
        numberLabel.setAlignment(Pos.CENTER_LEFT);
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
    }

    @Override
    protected void updateItem(Game game, boolean empty) {
        super.updateItem(game, empty);
        if (empty || game == null) {
            setGraphic(null);
        } else {
            numberLabel.setText(String.format("%d.", getIndex() + 1));

            if (game.imageURL() != null && !game.imageURL().isEmpty()) {
                try {
                    String imageUrl = String.format("http://media.steampowered.com/steamcommunity/public/images/apps/%d/%s.jpg",
                            game.appID(), game.imageURL());
                    imageView.setImage(new Image(imageUrl, true));
                } catch (Exception e) {
                    imageView.setImage(null);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Image Load Error");
                    alert.setHeaderText("Failed to load game image.");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            } else {
                imageView.setImage(null);
            }

            double hours = game.minutes() / 60.0;
            textLabel.setText(String.format("%s\n%.1f hours", game.gameName(), hours));

            setGraphic(hbox);
        }
    }
}
