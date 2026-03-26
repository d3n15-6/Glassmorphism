package style;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StyleRegistry {
    private Map<String, Style> styles = new HashMap<>();

    public void loadFromYAML(InputStream is) throws IOException {
        /* parse y probar el estilo */
        // Ya no usar.
        //Yaml yaml = new Yaml(new Constructor(Map.class));

        // Nuevo código.
        Yaml yaml = new Yaml();
        Map<String, Map<String, Object>> raw = yaml.load(is);

        for (var entry : raw.entrySet()) {
            String comp = entry.getKey();
            // Nuevo código.
            Object node = entry.getValue();
            // Nuevo if
            if (node instanceof Map<?,?> mapNode) {
                // Ya no usar.
                //Map<String, Object> vales = entry.getValue();
                // Nuevo código.
                Map<String , Object> vales = new HashMap<>();
                // Nuevo código.
                mapNode.forEach((k, v) -> vales.put(String.valueOf(k), v));
                Style style = parseStyle(vales);
                styles.put(comp, style);
            }

        }
    }

    // Nuevo método.
    public void getApplyPreset(String themeName) {
        String dir = "resources/themes/glass-" + themeName + ".yaml";
        // InputStream inputStream = Files.newInputStream(Paths.get(dir)) esto ya no se usa.
        try (InputStream inputStream = getClass().getResourceAsStream(dir)) {
            // Nuevo código.
            if (themeName == null) {
                System.err.println("[@]Tema (" + themeName + ") no encontrado");
                return;
            }
            loadFromYAML(inputStream);
            System.out.println("[@]Tema aplicado: " + themeName);
        } catch (IOException e) {
            System.err.println("[@]Error al cargar el tema ( " + themeName + " ) : " + e.getMessage());
        }
    }

    public Style getStyle(String component) {
        return styles.getOrDefault(component, new Style());
    }

    private Style parseStyle(Map<String, Object> map) {
        Style s = new Style();
        s.blurRadius = ((Number) map.getOrDefault("blurRadius", 0)).intValue();
        s.alpha = ((Number) map.getOrDefault("alpha", 1.0)).floatValue();

        return s;
    }
}
