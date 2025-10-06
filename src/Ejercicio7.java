import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio7 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        organizarBiblioteca();
    }

    public static void organizarBiblioteca() {
        System.out.print("Introduce la categoría del libro: ");
        String categoria = sc.nextLine();

        File carpeta = new File("Biblioteca/" + categoria);
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
            }
        }

        File catalogo = new File(carpeta, "catalogo.txt");
        try {
            if (catalogo.createNewFile()) {
                System.out.println("Archivo creado: " + catalogo.getAbsolutePath());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        verificarLibro(carpeta);
    }

    public static void verificarLibro(File carpeta) {
        System.out.print("Introduce el nombre del libro: ");
        String nombreLibro = sc.nextLine();
        File libro = new File(carpeta, nombreLibro + ".txt");

        if (libro.exists()) {
            System.out.println("El libro ya existe en la biblioteca.");
        } else {
            System.out.print("El libro no existe. ¿Deseas crearlo? (s/n): ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                try {
                    libro.createNewFile();
                    System.out.println("Libro creado: " + libro.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Error al crear el libro.");
                }
            }
        }
    }
}