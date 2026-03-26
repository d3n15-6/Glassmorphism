package com.glassmorphism.glassui;

import core.GlassPanel;
import style.Style;
import style.StyleRegistry;
import style.StyleWatcher;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GlassPanelDemo {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("GlassPanel Demo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);

                Path path = Paths.get("resources/themes/glass-theme.yaml");
                StyleRegistry registry = new StyleRegistry();
                registry.getApplyPreset("theme");
                //registry.loadFromYAML(Files.newInputStream(path));
                //registry.loadFromYAML(getClass.getResourceAsStream);
                Style style = registry.getStyle("GlassPanel");

                GlassPanel glassPanel = new GlassPanel(style);
                glassPanel.setBounds(50, 50, 400, 180);

                frame.setLayout(null);
                frame.add(glassPanel);
                frame.setVisible(true);

                Thread watcher = new Thread(new StyleWatcher(path, () ->{
                    try {
                        registry.loadFromYAML(Files.newInputStream(path));
                        SwingUtilities.invokeLater(() -> glassPanel.setStyle(registry.getStyle("GlassPanel")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
                watcher.setDaemon(true);
                watcher.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
