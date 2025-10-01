import java.io.File;

public class Ejercicio4 {

    public static void main(String[] args){

        String dirPadre = "C:\\Users\\AlumnoAfternoon\\Documents\\ProyectoJava";

        File directorio = new File(dirPadre);

        if (directorio.exists() && directorio.isDirectory()){
            String[] contenido = directorio.list();

            for (int i = 0; i < contenido.length; i++){
                System.out.println(contenido[i]);
            }

        } else {

            System.out.println("La siguiente ruta no es un directorio o no existe");
        }
    }
}
