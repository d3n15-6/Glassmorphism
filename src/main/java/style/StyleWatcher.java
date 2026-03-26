package style;


import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Hot-Reload de archivos YAML con WatchServices.
 * Observa cambios si un preset es modificado desde el disco (por ejemplo: cuando el diseñador guarda cambios
 * en el YAML), llamados cambios en el caliente.
 * */
public class StyleWatcher implements Runnable {
    private Path pathToFile;
    private Runnable onChange;

    public StyleWatcher(Path pathToFile, Runnable onChange) {
        this.pathToFile = pathToFile;
        this.onChange = onChange;
    }

    @Override
    public void run() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = pathToFile.getParent();
            dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path changed = ev.context();
                    if (changed.equals(pathToFile.getFileName())) {
                        System.out.println("[@] Estilo modificado: recargando...");
                        onChange.run(); // Callback externo.
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
