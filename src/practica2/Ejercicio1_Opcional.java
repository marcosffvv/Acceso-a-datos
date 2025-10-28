package practica2;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Ejercicio1_Opcional {
    public static void main(String[] args) throws Exception {
        Path json = Paths.get("config.json");
        Files.write(json, Arrays.asList("{", " \"host\": \"localhost\"", "}"), StandardCharsets.UTF_8);
        Map<String, String> cfg = leerJsonSimple(json.toString());
        System.out.println(cfg);
    }

    public static Map<String, String> leerJsonSimple(String archivo) throws IOException {
        Map<String, String> map = new LinkedHashMap<>();
        String content = Files.readString(Paths.get(archivo), StandardCharsets.UTF_8).replaceAll("[{}]", "").trim();
        if (!content.isEmpty()) {
            for (String pair : content.split(",")) {
                String[] kv = pair.split(":");
                if (kv.length == 2) map.put(kv[0].replaceAll("\"", "").trim(), kv[1].replaceAll("\"", "").trim());
            }
        }
        return map;
    }
}
