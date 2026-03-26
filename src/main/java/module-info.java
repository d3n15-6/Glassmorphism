module com.glassmorphism.glassui {
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.fxml;
    requires org.yaml.snakeyaml;


    opens com.glassmorphism.glassui to javafx.fxml;
    exports com.glassmorphism.glassui;
}