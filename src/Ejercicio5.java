import java.io.File;
import java.io.IOException;

public class Ejercicio5 {

    public static void main(String[] args) throws IOException {

        String directorio = "C:\\Users\\AlumnoAfternoon\\Documents\\ProyectoJava\\Padre";
        String archivo = "C:\\Users\\AlumnoAfternoon\\Documents\\ProyectoJava\\hijo.txt";

        //Crear instancias File utilizando su constructor
        File dirPadre = new File(directorio);
        File archivoHijo = new File(archivo);

        //Verificar si el archivo y directorio existen
        boolean fin = false;

        do {

            if (!dirPadre.exists() ) {
                System.out.println("El directorio no existe");
                dirPadre.mkdir();
                System.out.println("Directorio creado correctamente");

            } else if (!archivoHijo.exists()) {
                System.out.println("El archivo no existe");
                archivoHijo.createNewFile();
                System.out.println("Archivo creado correctamente");

            } else {
                System.out.println("El directorio ya existe");
                fin = true;
            }

        } while (!fin);
    }
}
