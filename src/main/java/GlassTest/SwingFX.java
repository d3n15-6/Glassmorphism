package GlassTest;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;

public class SwingFX {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame glassframe = new JFrame("Swing + JavaFX - Efecto Cristalino.");
            glassframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            glassframe.setSize(400, 300);
            glassframe.setLayout(new BorderLayout());
            // Panel que embebe JavaFX
            JFXPanel jfxPanel = new JFXPanel();
            jfxPanel.setPreferredSize(new Dimension(400, 260));

            glassframe.add(jfxPanel, BorderLayout.CENTER);
            glassframe.pack();
            glassframe.setLocationRelativeTo(null);
            glassframe.setVisible(true);

            // Inicializar escena FX
            Platform.runLater(() ->{
                // 1. Root Pane con blur
                StackPane root = new StackPane();

                // Texto flotando en la escena.
                Text texto = new Text("Efecto agua sobre cristal");
                texto.setFill(Color.web("#333333AA"));
                texto.setStyle("-fx-font-size: 20px; -fx-font-family: 'Segoe UI'");

                // Fondo con efecto blur.
                root.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1);");
                root.setEffect(new BoxBlur(15, 15, 3));
                root.getChildren().add(texto);

                // 2. Construir la escena.
                Scene scene = new Scene(root, 400, 400, Color.AQUA);
                jfxPanel.setScene(scene);
            });

        });
    }
}
