package practica2;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.nio.file.attribute.*;
import java.time.*;
import java.util.*;

public class Ejercicio3_Opcional {
    public static void main(String[] args) throws Exception {
        Path src = Paths.get("src_dir");
        Path dst = Paths.get("backup_dir");
        Files.createDirectories(src);
        Files.write(src.resolve("file.txt"), Arrays.asList("contenido"), StandardCharsets.UTF_8);
        backupIncremental(src.toString(), dst.toString(), dst.resolve(".lastbackup").toString());
    }

    public static int backupIncremental(String origen, String destino, String ctrl) throws IOException {
        Path src = Paths.get(origen);
        Path dst = Paths.get(destino);
        long ultimo = leerUltimo(ctrl);
        int copiados = 0;
        try (var stream = Files.walk(src)) {
            for (Path p : (Iterable<Path>) stream::iterator) {
                if (Files.isDirectory(p)) continue;
                BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
                if (attrs.lastModifiedTime().toMillis() > ultimo) {
                    Path rel = src.relativize(p);
                    Path destFile = dst.resolve(rel);
                    Files.createDirectories(destFile.getParent());
                    Files.copy(p, destFile, StandardCopyOption.REPLACE_EXISTING);
                    copiados++;
                }
            }
        }
        Files.writeString(Paths.get(ctrl), String.valueOf(System.currentTimeMillis()), StandardCharsets.UTF_8);
        return copiados;
    }

    static long leerUltimo(String ctrl) throws IOException {
        Path p = Paths.get(ctrl);
        if (!Files.exists(p)) return 0L;
        return Long.parseLong(Files.readString(p, StandardCharsets.UTF_8).trim());
    }
}
