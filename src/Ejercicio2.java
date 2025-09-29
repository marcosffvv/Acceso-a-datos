import java.io.File;

public class Ejercicio2 {
    public static void main(String[] args) {
        //Directorio padre que acabamos de crear
        File ruta = new File ("C:\\Users\\AlumnoAfternoon\\Documents\\ProyectoJava");

        //Verificar si la ruta existe
        if (!ruta.exists()){
            //Verificar si la ruta es un directorio
            if (!ruta.isDirectory()){
                //Si es un directorio, se muestra el mensaje
                System.out.println("La ruta presenta un directorio en: " + ruta.getAbsolutePath());
            } else if (!ruta.isFile()){
                //Si es un archivo, se muestra el mensaje
                System.out.println("La ruta presenta un archivo en: " + ruta.getAbsolutePath());
            }
        } 
    }
}