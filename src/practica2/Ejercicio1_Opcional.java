package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Ejercicio1_Opcional {
    public static void main(String[] args) throws Exception {

        // Crea un archivo JSON simple llamado "config.json" con un contenido de ejemplo
        Path json = Paths.get("config.json");
        Files.write(json, Arrays.asList("{", " \"host\": \"localhost\"", "}"), StandardCharsets.UTF_8);

        // Llama al método que lee y analiza el archivo JSON
        Map<String, String> cfg = leerJsonSimple(json.toString());

        // Muestra el contenido del mapa resultante (clave=valor)
        System.out.println(cfg);
    }

    // Método que lee un archivo JSON muy simple y lo convierte en un mapa (clave-valor)
    public static Map<String, String> leerJsonSimple(String archivo) throws IOException {
        Map<String, String> map = new LinkedHashMap<>();

        // Lee todo el contenido del archivo como texto (UTF-8)
        String content = Files.readString(Paths.get(archivo), StandardCharsets.UTF_8)
                .replaceAll("[{}]", "") // Elimina las llaves del JSON
                .trim();

        // Si el contenido no está vacío, procesa cada par clave:valor
        if (!content.isEmpty()) {
            for (String pair : content.split(",")) {
                String[] kv = pair.split(":");
                // Si hay exactamente dos partes (clave y valor), las limpia y guarda
                if (kv.length == 2)
                    map.put(
                            kv[0].replaceAll("\"", "").trim(),  // Limpia comillas y espacios de la clave
                            kv[1].replaceAll("\"", "").trim()   // Limpia comillas y espacios del valor
                    );
            }
        }

        // Devuelve el mapa con los pares extraídos del JSON
        return map;
    }
}