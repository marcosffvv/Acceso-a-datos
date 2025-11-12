package practica1;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class Ejercicio9 {

    static Scanner sc = new Scanner(System.in);
    // Scanner global para leer entradas del usuario

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu(); // Muestra el menú de opciones
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpia el buffer del scanner

            // Estructura switch con sintaxis moderna (Java 14+)
            switch (opcion) {
                case 1 -> verificarArchivo();     // Comprueba si un archivo existe o lo crea
                case 2 -> explorarDirectorio();   // Muestra el contenido de un directorio
                case 3 -> crearCarpeta();         // Crea una nueva carpeta
                case 4 -> crearArchivo();         // Crea un nuevo archivo
                case 5 -> trabajarConURI();       // Convierte una ruta a formato URI
                case 6 -> System.out.println("Saliendo del asistente...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6); // Repite hasta que el usuario elija salir
    }

    public static void mostrarMenu() {
        // Muestra las opciones disponibles del asistente de archivos
        System.out.println("\n===== ASISTENTE DE ARCHIVOS =====");
        System.out.println("1. Verificar archivo");
        System.out.println("2. Explorar carpeta");
        System.out.println("3. Crear carpeta");
        System.out.println("4. Crear archivo");
        System.out.println("5. Trabajar con URIs");
        System.out.println("6. Salir");
    }

    public static void verificarArchivo() {
        System.out.print("Ruta del archivo: ");
        String ruta = sc.nextLine();
        File archivo = new File(ruta);

        if (archivo.exists()) {
            System.out.println("El archivo existe.");
        } else {
            // Si el archivo no existe, se da la opción de crearlo
            System.out.print("No existe. ¿Deseas crearlo? (s/n): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                try {
                    archivo.getParentFile().mkdirs(); // Crea carpetas intermedias si es necesario
                    archivo.createNewFile();          // Crea el archivo
                    System.out.println("Archivo creado.");
                } catch (IOException e) {
                    System.out.println("Error al crear archivo: " + e.getMessage());
                }
            }
        }
    }

    public static void explorarDirectorio() {
        System.out.print("Ruta del directorio: ");
        String ruta = sc.nextLine();
        File dir = new File(ruta);

        if (dir.exists() && dir.isDirectory()) {
            // Recorre el contenido del directorio
            for (String nombre : dir.list()) {
                File elemento = new File(dir, nombre);
                // Identifica si es archivo o carpeta
                if (elemento.isFile()) System.out.println(nombre + " [ARCHIVO]");
                else if (elemento.isDirectory()) System.out.println(nombre + " [DIRECTORIO]");
            }
        } else {
            System.out.println("Directorio no válido.");
        }
    }

    public static void crearCarpeta() {
        System.out.print("Ruta de la carpeta a crear: ");
        String ruta = sc.nextLine();
        File carpeta = new File(ruta);

        // mkdirs() crea toda la ruta de carpetas si no existen
        if (carpeta.mkdirs()) {
            System.out.println("Carpeta creada correctamente.");
        } else {
            System.out.println("No se pudo crear la carpeta (puede que ya exista).");
        }
    }

    public static void crearArchivo() {
        System.out.print("Ruta del archivo a crear: ");
        String ruta = sc.nextLine();
        File archivo = new File(ruta);
        try {
            archivo.getParentFile().mkdirs(); // Crea las carpetas contenedoras si no existen
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado con éxito.");
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public static void trabajarConURI() {
        System.out.print("Ruta para convertir a URI: ");
        String ruta = sc.nextLine();
        File archivo = new File(ruta);
        URI uri = archivo.toURI(); // Convierte la ruta a un objeto URI
        System.out.println("URI generado: " + uri);
    }
}