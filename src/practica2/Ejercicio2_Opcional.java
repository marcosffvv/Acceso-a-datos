package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Ejercicio2_Opcional {

    // Mapa estático para almacenar las variables de entorno cargadas desde el archivo
    private static final Map<String, String> ENV = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // Crea un archivo .env de ejemplo con dos variables
        Path env = Paths.get(".env");
        Files.write(env, Arrays.asList("VAR1=valor1", "VAR2=valor2"), StandardCharsets.UTF_8);

        // Carga las variables desde el archivo .env al mapa ENV
        cargarEnv(env.toString());

        // Muestra las variables cargadas en consola
        System.out.println(ENV);
    }

    /**
     * Carga un archivo de entorno (.env) y almacena las variables en el mapa ENV.
     *
     * Cada línea debe tener el formato "CLAVE=VALOR".
     * Se ignoran las líneas vacías o que comiencen con '#'.
     */
    public static void cargarEnv(String archivoEnv) throws IOException {
        // Lee el archivo línea por línea usando BufferedReader con codificación UTF-8
        try (BufferedReader br = Files.newBufferedReader(Paths.get(archivoEnv), StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Ignora líneas vacías o comentarios
                if (linea.trim().isEmpty() || linea.startsWith("#")) continue;

                // Divide cada línea en dos partes: clave y valor (solo en el primer '=' encontrado)
                String[] kv = linea.split("=", 2);

                // Si hay una clave y un valor válidos, los almacena en el mapa ENV
                if (kv.length == 2) ENV.put(kv[0], kv[1]);
            }
        }
    }
}