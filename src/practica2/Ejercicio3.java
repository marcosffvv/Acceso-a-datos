package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;

public class Ejercicio3 {

    enum NivelLog { INFO, WARNING, ERROR }
    private final String archivoLog = "app.log";
    private final long tamMax = 1024;
    private int rotacion = 0;

    public static void main(String[] args) throws Exception {
        Ejercicio3 e = new Ejercicio3();
        for (int i = 0; i < 200; i++) e.escribirLog("Mensaje " + i, NivelLog.WARNING);
    }

    public void escribirLog(String mensaje, NivelLog nivel) throws IOException {
        rotarSiNecesario();
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(archivoLog), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write("[" + ts + "] [" + nivel + "] " + mensaje);
            bw.newLine();
        }
    }

    private void rotarSiNecesario() throws IOException {
        Path p = Paths.get(archivoLog);
        if (Files.exists(p) && Files.size(p) > tamMax) {
            rotacion++;
            Files.move(p, Paths.get(archivoLog + "." + rotacion), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}