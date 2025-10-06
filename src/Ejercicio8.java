import java.io.File;
import java.net.URI;

public class Ejercicio8 {

    public static void main(String[] args) {
        explorarCarpeta("Biblioteca");
    }

    public static void explorarCarpeta(String ruta) {
        File directorio = new File(ruta);
        if (directorio.exists() && directorio.isDirectory()) {
            String[] contenido = directorio.list();
            if (contenido != null) {
                for (String nombre : contenido) {
                    analizarElemento(new File(directorio, nombre));
                }
            }
        } else {
            System.out.println("La ruta no existe o no es un directorio.");
        }
    }

    public static void analizarElemento(File elemento) {
        if (elemento.isFile()) {
            System.out.println(elemento.getName() + " [ARCHIVO] - Tamaño: " + elemento.length() + " bytes");
        } else if (elemento.isDirectory()) {
            String[] contenido = elemento.list();
            int cantidad = (contenido != null) ? contenido.length : 0;
            System.out.println(elemento.getName() + " [DIRECTORIO] - Contiene " + cantidad + " elementos");
        }
        System.out.println("URI: " + convertirAURI(elemento.getAbsolutePath()));
    }

    public static URI convertirAURI(String ruta) {
        File archivo = new File(ruta);
        return archivo.toURI();
    }
}
