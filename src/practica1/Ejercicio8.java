package practica1;

import java.io.File;
import java.net.URI;
import java.util.Scanner;

public class Ejercicio8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== EXPLORADOR INTELIGENTE ===");
        System.out.print("Introduce la ruta a explorar: ");
        String ruta = sc.nextLine();

        File directorio = new File(ruta);
        // Crea un objeto File que representa el directorio que el usuario quiere explorar

        if (directorio.exists() && directorio.isDirectory()) {
            // Verifica que la ruta existe y que efectivamente es un directorio

            System.out.println("\nExplorando: " + directorio.getAbsolutePath());
            String[] contenido = directorio.list();
            // Obtiene una lista con los nombres de los archivos y carpetas dentro del directorio

            if (contenido != null && contenido.length > 0) {
                // Comprueba que el directorio no esté vacío

                int contador = 0;
                for (String nombre : contenido) {
                    File elemento = new File(directorio, nombre);
                    // Crea un objeto File para cada elemento encontrado

                    if (elemento.isDirectory()) {
                        // Si el elemento es un directorio, muestra su nombre y el número de elementos que contiene
                        String[] sub = elemento.list();
                        int total = (sub != null) ? sub.length : 0;
                        System.out.println("- " + elemento.getName() + " [DIRECTORIO - " + total + " elementos]");
                    } else if (elemento.isFile()) {
                        // Si es un archivo, muestra su nombre y su tamaño en bytes
                        System.out.println("- " + elemento.getName() + " [ARCHIVO - " + elemento.length() + " bytes]");
                    }
                    contador++;
                }
                System.out.println("\nTotal de elementos encontrados: " + contador);
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            // Si la ruta no existe o no es un directorio válido
            System.out.println("✗ La ruta no existe: " + ruta);
        }

        // --- Conversión a URI ---
        System.out.println("\nCONVERSIÓN A URI:");
        System.out.println("Ruta original: " + directorio.getAbsolutePath());

        URI uri = directorio.toURI();
        // Convierte la ruta del directorio a un objeto URI (identificador uniforme de recursos)
        System.out.println("URI equivalente: " + uri);

        // Validación de la URI
        File comprobacion = new File(uri);
        // Crea un nuevo objeto File a partir de la URI para comprobar que ambas rutas coinciden
        if (comprobacion.equals(directorio)) {
            System.out.println("✓ La URI es válida y apunta al mismo elemento");
        } else {
            System.out.println("✗ Hay un problema con la conversión a URI");
        }

        sc.close();
        // Cierra el Scanner para liberar recursos
    }
}