package practica2;

import java.nio.file.*;
import java.io.*;
import java.nio.charset.*;
import java.util.Arrays;

public class Ejercicio2 {

    public static void main(String[] args) {
        try {
            // Crea dos archivos de texto de ejemplo con contenido diferente
            Path a1 = Paths.get("archivo1.txt");
            Path a2 = Paths.get("archivo2.txt");

            Files.write(a1, Arrays.asList("Java es poderoso", "Python también", "Java es popular"), StandardCharsets.UTF_8);
            Files.write(a2, Arrays.asList("JavaScript en web", "Java en backend"), StandardCharsets.UTF_8);

            // Llama al método que combina los archivos en uno solo, filtrando por la palabra "Java"
            combinarArchivos(new String[]{a1.toString(), a2.toString()}, "combinado.txt", "Java");

        } catch (Exception e) {
            e.printStackTrace(); // Muestra un error si algo falla
        }
    }

    /**
     * Combina varios archivos de texto en uno solo.
     * Solo copia las líneas que contienen la palabra indicada en 'filtro'.
     *
     * @param archivosEntrada  Lista de rutas de archivos a combinar
     * @param archivoSalida    Archivo de salida donde se escriben los resultados
     * @param filtro           Palabra que debe aparecer en las líneas copiadas (puede ser null)
     * @return                 Número total de líneas escritas en el archivo combinado
     */
    public static int combinarArchivos(String[] archivosEntrada, String archivoSalida, String filtro) throws IOException {
        int total = 0; // Contador de líneas escritas

        // Abre un BufferedWriter para escribir en el archivo de salida (UTF-8)
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(archivoSalida), StandardCharsets.UTF_8)) {

            // Recorre cada archivo de entrada
            for (String ruta : archivosEntrada) {
                try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta), StandardCharsets.UTF_8)) {
                    String linea;
                    // Lee cada línea del archivo
                    while ((linea = br.readLine()) != null) {
                        // Si no hay filtro o la línea contiene la palabra clave, se escribe
                        if (filtro == null || linea.contains(filtro)) {
                            bw.write(linea);
                            bw.newLine();
                            total++; // Incrementa el contador de líneas escritas
                        }
                    }
                }
            }
        }
        return total; // Devuelve el total de líneas copiadas
    }
}