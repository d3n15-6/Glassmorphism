package GlassTest;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;

import static javafx.scene.paint.Color.web;

public class SwingWithFX {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame glassframe = new JFrame("Glass in Swing");
            glassframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            glassframe.setSize(400, 400);
            //
            JFXPanel jfxPanel = new JFXPanel();
            glassframe.add(jfxPanel);
            glassframe.setVisible(true);
            // Inicializar escena FX
            Platform.runLater(() ->{
                // 1. Root Pane con blur
                StackPane root = new StackPane();
                Rectangle glass = new Rectangle(400, 400, Color.web("#FFFFFF80"));
                root.setEffect(new BoxBlur(15, 15, 3));
                root.getChildren().add(glass);
                root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
                // 2. Construir la escena.
                Scene scene = new Scene(root, 400, 400, Color.TRANSPARENT);
                jfxPanel.setScene(scene);
            });

        });
    }
}
