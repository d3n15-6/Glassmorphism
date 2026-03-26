package com.glassmorphism.glassui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class HelloController {
    @FXML
    private Label welcomeText;
    private Stage stage;

    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
        //start();
    }

    public void buttonLiquid() {
        Button btn = new Button();
        btn.setText("Boton con CSS");
        btn.getStyleClass().add("src/main/resources/css/liquid-button.css");

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("Boton Liquido con css");
        stage.setScene(scene);
        stage.show();
    }
}