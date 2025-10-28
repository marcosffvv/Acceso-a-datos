package iniciales;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class Ejercicio3 {

    public static void main(String[] args) {
        
        try {

            String uriString = "C:/Users/AlumnoAfternoon/Documents/ProyectoJava";
            URI uri = new URI(uriString);

            File ruta = new File(uri);

            if  (ruta.exists()){

                if (ruta.isDirectory()){
                    //Si es un directorio, se muestra el mensaje
                    System.out.println("La ruta presenta un directorio en: " + uri.toString());
                } else if (ruta.isFile()){
                    //Si es un archivo, se muestra el mensaje
                    System.out.println("La ruta presenta un archivo en: " + uri.toString());
                }
            }
            else {
                System.out.println("La URI no existe: " + uri.toString());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
            
        }
    }
}