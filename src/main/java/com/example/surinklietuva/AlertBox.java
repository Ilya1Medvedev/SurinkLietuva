package com.example.surinklietuva;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    private static int MIN_WIDTH = 250;

    public static void display(String title, String message) {
        Stage window = new Stage();

        Label label = new Label();
        label.setText(message);
        Button button = new Button("OK");
        button.setOnAction(e -> window.close());

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(MIN_WIDTH);

        VBox layout = new VBox();
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
