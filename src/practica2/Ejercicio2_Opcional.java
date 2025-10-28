package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Ejercicio2_Opcional {

    private static final Map<String, String> ENV = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Path env = Paths.get(".env");
        Files.write(env, Arrays.asList("VAR1=valor1", "VAR2=valor2"), StandardCharsets.UTF_8);
        cargarEnv(env.toString());
        System.out.println(ENV);
    }

    public static void cargarEnv(String archivoEnv) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(archivoEnv), StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || linea.startsWith("#")) continue;
                String[] kv = linea.split("=", 2);
                if (kv.length == 2) ENV.put(kv[0], kv[1]);
            }
        }
    }
}
