package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {

            Path entrada = Paths.get("entrada_ej1.txt");
            Files.write(entrada, Arrays.asList("Hola mundo", "Java es genial", "Programación"), StandardCharsets.UTF_8);

            EstadisticasTexto est = analizarArchivo(entrada.toString());
            System.out.println(est.formatoBonito());

            Path salida = Paths.get("estadisticas_salida.txt");
            guardarEstadisticas(est, salida.toString());

            System.out.println("Estadísticas guardadas en: " + salida.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class EstadisticasTexto {
        private int numeroLineas;
        private int numeroPalabras;
        private int numeroCaracteres;
        private String palabraMasLarga;

        public EstadisticasTexto(int numeroLineas, int numeroPalabras, int numeroCaracteres, String palabraMasLarga) {
            this.numeroLineas = numeroLineas;
            this.numeroPalabras = numeroPalabras;
            this.numeroCaracteres = numeroCaracteres;
            this.palabraMasLarga = palabraMasLarga;
        }

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

    public static EstadisticasTexto analizarArchivo(String nombreArchivo) throws IOException {

        Path path = Paths.get(nombreArchivo);
        int lineas = 0, palabras = 0, caracteres = 0;
        String masLarga = null;
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length() + 1;
                String[] tokens = linea.trim().isEmpty() ? new String[0] : linea.trim().split("\\s+");
                palabras += tokens.length;
                for (String t : tokens) {
                    if (masLarga == null || t.length() > masLarga.length()) masLarga = t;
                }
            }
        }
        return new EstadisticasTexto(lineas, palabras, caracteres, masLarga);
    }

    public static void guardarEstadisticas(EstadisticasTexto est, String archivoSalida) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(archivoSalida), StandardCharsets.UTF_8)) {
            bw.write(est.formatoBonito());
        }
    }
}