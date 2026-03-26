package GlassTest;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ComplexGlassDemo {
    private JLabel swingLabel;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ComplexGlassDemo().initUi());
    }

    private void initUi() {
        // 1. ventana swing con contenido de ejemplo.
        JFrame frame = new JFrame("Demo Swing + JavaFX Glass");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel base swing.
        JPanel panel = new JPanel();
        panel.setBackground(java.awt.Color.DARK_GRAY);
        panel.setLayout(new FlowLayout());
        swingLabel = new JLabel("Texto swing demo.");
        swingLabel.setForeground(java.awt.Color.WHITE);
        panel.add(swingLabel);
        frame.add(panel, BorderLayout.CENTER);

        // Capa superior: JLayeredPane para superponer JavaFX
        JLayeredPane layered = new JLayeredPane();
        frame.setGlassPane(layered);
        layered.setVisible(true);

        // 2. JFXPanel transparente.
        JFXPanel jfxPanel = new JFXPanel();
        jfxPanel.setOpaque(false); // Asignar transparencia.

        // Ajustar el tamaño del JFXPanel al layeredPane
        layered.setLayout(new BorderLayout());
        jfxPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layered.add(jfxPanel, BorderLayout.CENTER);
        // 3. Inicializar la escena JavaFX antes de mostrar el frame.
        this.initJavaFX(jfxPanel, frame);

        // 3. Listener para redimensionar.
        frame.addComponentListener((new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //super.componentResized(e);
                layered.setSize(frame.getSize());
                jfxPanel.setSize(frame.getSize());
            }
        }));

        frame.setVisible(true);
    }

    private void initJavaFX(JFXPanel jfxPanel, JFrame frame) {
        // 3. Incializar escena JavaFX.
        Platform.runLater(() -> {
            try {
                StackPane stackPane = new StackPane();
                stackPane.setPrefSize(frame.getWidth(), frame.getHeight());

                // Un rectángulo semitransparente y difuminado.
                Rectangle glass = new Rectangle();
                glass.widthProperty().bind(stackPane.widthProperty());
                glass.heightProperty().bind(stackPane.heightProperty());
                glass.setFill(Color.web("#FFFFFF20"));
                glass.setEffect(new BoxBlur(30, 30, 3));

                Button fxButton = new Button("Actualizar Swing");
                fxButton.setOnAction(event -> updateSwingLabel("Swing Actualizado desde JavaFX."));
                fxButton.setStyle("-fx-font-size: 14px; -fx-background-radius: 8;");

                stackPane.getChildren().addAll(glass, fxButton);

                Scene scene = new Scene(stackPane);
                scene.setFill(Color.TRANSPARENT);

                stackPane.prefWidthProperty().bind(scene.widthProperty());
                stackPane.prefHeightProperty().bind(scene.heightProperty());

                jfxPanel.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al inicializar JavaFX: " + e.getMessage());
            }
        });
    }

    private void updateSwingLabel(String text) {
        SwingUtilities.invokeLater(() -> swingLabel.setText(text));
    }
}
