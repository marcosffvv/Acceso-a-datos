package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;

public class Ejercicio3 {

    // Enumeración para definir los niveles de log posibles
    enum NivelLog { INFO, WARNING, ERROR }

    // Nombre base del archivo de log
    private final String archivoLog = "app.log";
    // Tamaño máximo permitido (en bytes) antes de hacer rotación del log
    private final long tamMax = 1024;
    // Contador para numerar las rotaciones de archivo
    private int rotacion = 0;

    public static void main(String[] args) throws Exception {
        Ejercicio3 e = new Ejercicio3();
        // Escribe 200 mensajes de log de nivel WARNING
        for (int i = 0; i < 200; i++)
            e.escribirLog("Mensaje " + i, NivelLog.WARNING);
    }

    /**
     * Escribe un mensaje en el archivo de log, incluyendo marca de tiempo y nivel.
     * Antes de escribir, verifica si es necesario rotar el archivo.
     */
    public void escribirLog(String mensaje, NivelLog nivel) throws IOException {
        rotarSiNecesario(); // Verifica si el log superó el tamaño máximo y lo rota

        // Obtiene la fecha y hora actual con formato legible
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Abre el archivo de log en modo append (agregar al final)
        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get(archivoLog),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            // Escribe una línea con el formato: [fecha] [nivel] mensaje
            bw.write("[" + ts + "] [" + nivel + "] " + mensaje);
            bw.newLine(); // Salto de línea al final
        }
    }

    /**
     * Verifica si el archivo de log ha superado el tamaño máximo.
     * Si es así, lo renombra (rota) con un número incremental.
     * Ejemplo: app.log → app.log.1, app.log.2, etc.
     */
    private void rotarSiNecesario() throws IOException {
        Path p = Paths.get(archivoLog);
        if (Files.exists(p) && Files.size(p) > tamMax) {
            rotacion++; // Incrementa el contador de rotaciones
            Files.move(p, Paths.get(archivoLog + "." + rotacion), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}