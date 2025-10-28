package practica2;

import java.nio.file.*;
import java.io.*;
import java.nio.charset.*;
import java.util.Arrays;

public class Ejercicio2 {

    public static void main(String[] args) {
        try {

            Path a1 = Paths.get("archivo1.txt");
            Path a2 = Paths.get("archivo2.txt");
            Files.write(a1, Arrays.asList("Java es poderoso", "Python también", "Java es popular"), StandardCharsets.UTF_8);
            Files.write(a2, Arrays.asList("JavaScript en web", "Java en backend"), StandardCharsets.UTF_8);

            combinarArchivos(new String[]{a1.toString(), a2.toString()}, "combinado.txt", "Java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int combinarArchivos(String[] archivosEntrada, String archivoSalida, String filtro) throws IOException {
        int total = 0;
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(archivoSalida), StandardCharsets.UTF_8)) {
            for (String ruta : archivosEntrada) {
                try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta), StandardCharsets.UTF_8)) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (filtro == null || linea.contains(filtro)) {
                            bw.write(linea);
                            bw.newLine();
                            total++;
                        }
                    }
                }
            }
        }
        return total;
    }
}