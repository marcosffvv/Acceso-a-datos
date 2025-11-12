package practica1;
// Define el paquete al que pertenece esta clase (organización del código en Java)

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
// Importa clases necesarias para manejar archivos y leer datos desde consola

public class Ejercicio7 {

    static Scanner sc = new Scanner(System.in);
    // Crea un objeto Scanner estático para leer entradas del usuario desde la consola

    public static void main(String[] args) {
        organizarBiblioteca();
        // Llama al método principal que gestiona la creación de carpetas y archivos
    }

    public static void organizarBiblioteca() {
        System.out.print("Introduce la categoría del libro: ");
        String categoria = sc.nextLine();
        // Solicita al usuario una categoría (por ejemplo, "Novela", "Ciencia", etc.)

        File carpeta = new File("Biblioteca/" + categoria);
        // Crea un objeto File que representa la ruta de la carpeta de esa categoría

        if (!carpeta.exists()) {
            // Si la carpeta no existe...
            if (carpeta.mkdirs()) {
                // ...la crea (incluso subcarpetas si es necesario)
                System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
                // Muestra la ruta completa de la carpeta creada
            }
        }

        File catalogo = new File(carpeta, "catalogo.txt");
        // Crea un objeto File que representa el archivo "catalogo.txt" dentro de la carpeta

        try {
            if (catalogo.createNewFile()) {
                // Intenta crear el archivo; si no existía, lo crea
                System.out.println("Archivo creado: " + catalogo.getAbsolutePath());
            } else {
                // Si ya existía, lo indica
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            // Captura y muestra un error si ocurre un problema al crear el archivo
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        verificarLibro(carpeta);
        // Llama al método que comprueba si un libro ya existe en esa categoría
    }

    public static void verificarLibro(File carpeta) {
        System.out.print("Introduce el nombre del libro: ");
        String nombreLibro = sc.nextLine();
        // Pide al usuario el nombre del libro

        File libro = new File(carpeta, nombreLibro + ".txt");
        // Crea un objeto File que representa el archivo del libro dentro de la carpeta

        if (libro.exists()) {
            // Si el archivo del libro ya existe...
            System.out.println("El libro ya existe en la biblioteca.");
        } else {
            // Si el libro no existe...
            System.out.print("El libro no existe. ¿Deseas crearlo? (s/n): ");
            String respuesta = sc.nextLine();
            // Pregunta al usuario si desea crearlo

            if (respuesta.equalsIgnoreCase("s")) {
                // Si el usuario responde "s" o "S" (sí)...
                try {
                    libro.createNewFile();
                    // Crea el nuevo archivo para el libro
                    System.out.println("Libro creado: " + libro.getAbsolutePath());
                    // Muestra la ruta del nuevo libro
                } catch (IOException e) {
                    // Captura un posible error de creación
                    System.out.println("Error al crear el libro.");
                }
            }
        }
    }
}