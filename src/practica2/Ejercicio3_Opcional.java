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
        Files.createDirectories(src); // Asegura que exista el directorio de origen
        Files.write(src.resolve("file.txt"), Arrays.asList("contenido"), StandardCharsets.UTF_8);
        // Llama al backup incremental indicando origen, destino y archivo de control (marca de tiempo)
        backupIncremental(src.toString(), dst.toString(), dst.resolve(".lastbackup").toString());
    }

    /**
     * Realiza un backup incremental copiando solo archivos modificados
     * después de la última marca guardada en 'ctrl'.
     *
     * @return número de archivos copiados
     */
    public static int backupIncremental(String origen, String destino, String ctrl) throws IOException {
        Path src = Paths.get(origen);
        Path dst = Paths.get(destino);

        long ultimo = leerUltimo(ctrl); // Lee la última marca (milis) del archivo de control
        int copiados = 0;

        // Recorre recursivamente todos los ficheros bajo 'src'
        try (var stream = Files.walk(src)) {
            for (Path p : (Iterable<Path>) stream::iterator) {
                if (Files.isDirectory(p)) continue; // Ignora directorios

                // Obtiene atributos para comprobar la fecha de modificación
                BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);

                // Si el archivo fue modificado después de la última copia, se copia
                if (attrs.lastModifiedTime().toMillis() > ultimo) {
                    Path rel = src.relativize(p);          // Ruta relativa respecto a src
                    Path destFile = dst.resolve(rel);     // Ruta destino manteniendo estructura
                    Files.createDirectories(destFile.getParent()); // Crea directorios padre si falta
                    Files.copy(p, destFile, StandardCopyOption.REPLACE_EXISTING); // Copia el archivo
                    copiados++;
                }
            }
        }

        // Actualiza el archivo de control con el timestamp actual (para el siguiente incremental)
        Files.writeString(Paths.get(ctrl), String.valueOf(System.currentTimeMillis()), StandardCharsets.UTF_8);
        return copiados;
    }

    // Lee la última marca de tiempo del archivo de control; si no existe devuelve 0
    static long leerUltimo(String ctrl) throws IOException {
        Path p = Paths.get(ctrl);
        if (!Files.exists(p)) return 0L;
        return Long.parseLong(Files.readString(p, StandardCharsets.UTF_8).trim());
    }
}