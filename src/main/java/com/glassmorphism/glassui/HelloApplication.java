package com.glassmorphism.glassui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
    //@Override
    public void start(Stage stage) throws IOException{
        Button btn = new Button();
        btn.getStyleClass().add("liquid-button");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 250);

        root.getChildren().add(btn);

        btn.setText("Boton con CSS");

        String pathCSS = getClass().getResource("/css/liquid-button.css").toExternalForm();
        btn.getStylesheets().add(pathCSS);

        stage.setTitle("Boton Liquido con css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}