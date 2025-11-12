package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            // Crea un archivo de entrada con contenido de ejemplo
            Path entrada = Paths.get("entrada_ej1.txt");
            Files.write(entrada, Arrays.asList("Hola mundo", "Java es genial", "Programación"), StandardCharsets.UTF_8);

            // Analiza el archivo y obtiene las estadísticas de texto
            EstadisticasTexto est = analizarArchivo(entrada.toString());
            System.out.println(est.formatoBonito()); // Muestra los resultados en consola

            // Guarda las estadísticas en un archivo de salida
            Path salida = Paths.get("estadisticas_salida.txt");
            guardarEstadisticas(est, salida.toString());

            System.out.println("Estadísticas guardadas en: " + salida.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error si ocurre alguna excepción
        }
    }

    // Clase interna para almacenar y mostrar estadísticas del texto analizado
    static class EstadisticasTexto {
        private int numeroLineas;
        private int numeroPalabras;
        private int numeroCaracteres;
        private String palabraMasLarga;

        // Constructor que inicializa los datos
        public EstadisticasTexto(int numeroLineas, int numeroPalabras, int numeroCaracteres, String palabraMasLarga) {
            this.numeroLineas = numeroLineas;
            this.numeroPalabras = numeroPalabras;
            this.numeroCaracteres = numeroCaracteres;
            this.palabraMasLarga = palabraMasLarga;
        }

        // Devuelve un texto formateado con las estadísticas calculadas
        public String formatoBonito() {
            int len = palabraMasLarga == null ? 0 : palabraMasLarga.length();
            return new StringBuilder()
                    .append("=== Estadísticas del archivo ===\n")
                    .append("Líneas: ").append(numeroLineas).append('\n')
                    .append("Palabras: ").append(numeroPalabras).append('\n')
                    .append("Caracteres: ").append(numeroCaracteres).append('\n')
                    .append("Palabra más larga: ")
                    .append(palabraMasLarga == null ? "(ninguna)" : palabraMasLarga + " (" + len + " caracteres)")
                    .toString();
        }
    }

    // Analiza el archivo línea por línea y calcula estadísticas básicas
    public static EstadisticasTexto analizarArchivo(String nombreArchivo) throws IOException {

        Path path = Paths.get(nombreArchivo);
        int lineas = 0, palabras = 0, caracteres = 0;
        String masLarga = null;

        // Usa un BufferedReader con codificación UTF-8 para leer el archivo
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++; // Cuenta una línea más
                caracteres += linea.length() + 1; // Suma caracteres (+1 para salto de línea)

                // Divide la línea en palabras separadas por espacios
                String[] tokens = linea.trim().isEmpty() ? new String[0] : linea.trim().split("\\s+");
                palabras += tokens.length;

                // Busca la palabra más larga del archivo
                for (String t : tokens) {
                    if (masLarga == null || t.length() > masLarga.length()) masLarga = t;
                }
            }
        }
        // Devuelve un objeto con todas las estadísticas calculadas
        return new EstadisticasTexto(lineas, palabras, caracteres, masLarga);
    }

    // Guarda las estadísticas generadas en un archivo de texto
    public static void guardarEstadisticas(EstadisticasTexto est, String archivoSalida) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(archivoSalida), StandardCharsets.UTF_8)) {
            bw.write(est.formatoBonito());
        }
    }
}