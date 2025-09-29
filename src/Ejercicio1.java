import java.io.File;

public class Ejercicio1 {
    public static void main(String[] args) {
        //Directorio padre que acabamos de crear
        File dirPadre = new File ("C:\\Users\\AlumnoAfternoon\\Documents\\ProyectoJava");

        //Nombre o ruta relativa al fichero que acabo de crear
        String nomHijo = "hijo.txt";

        //Creo una instancia File utilizando el constructor y la variable de arriba
        File archivo = new File(dirPadre, nomHijo);

        //Verificar si el archivo existe
        if (archivo.exists()) {
            System.out.println("Archivo encontrado en la ruta: " + archivo.getAbsolutePath());
        } else  {
            System.out.println("Archivo NO encontrado en la ruta: " + archivo.getAbsolutePath());
        }
    }
}