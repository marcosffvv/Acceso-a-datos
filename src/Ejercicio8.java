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

        if (directorio.exists() && directorio.isDirectory()) {
            System.out.println("\nExplorando: " + directorio.getAbsolutePath());
            String[] contenido = directorio.list();

            if (contenido != null && contenido.length > 0) {
                int contador = 0;
                for (String nombre : contenido) {
                    File elemento = new File(directorio, nombre);
                    if (elemento.isDirectory()) {
                        String[] sub = elemento.list();
                        int total = (sub != null) ? sub.length : 0;
                        System.out.println("- " + elemento.getName() + " [DIRECTORIO - " + total + " elementos]");
                    } else if (elemento.isFile()) {
                        System.out.println("- " + elemento.getName() + " [ARCHIVO - " + elemento.length() + " bytes]");
                    }
                    contador++;
                }
                System.out.println("\nTotal de elementos encontrados: " + contador);
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            System.out.println("✗ La ruta no existe: " + ruta);
        }

        // --- Conversión a URI ---
        System.out.println("\nCONVERSIÓN A URI:");
        System.out.println("Ruta original: " + directorio.getAbsolutePath());

        URI uri = directorio.toURI();
        System.out.println("URI equivalente: " + uri);

        // Validación de la URI
        File comprobacion = new File(uri);
        if (comprobacion.equals(directorio)) {
            System.out.println("✓ La URI es válida y apunta al mismo elemento");
        } else {
            System.out.println("✗ Hay un problema con la conversión a URI");
        }

        sc.close();
    }
}