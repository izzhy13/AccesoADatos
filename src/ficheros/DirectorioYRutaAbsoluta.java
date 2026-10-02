package ficheros;

import java.io.File;
import java.nio.file.Path;

public class DirectorioYRutaAbsoluta {

	public static void main(String[] args) {

		String directorioActual = System.getProperty("user.dir");
		System.out.println("Mi directorio de trabajo actual es " + directorioActual);

		String rutaRelativa = "fichero.txt";
		File fichero = new File(rutaRelativa);
		System.out.println("Ruta absoluta del fichero relativo: " + fichero.getAbsolutePath());
	}
	
}

