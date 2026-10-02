package ficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CrearFicheroEnRuta {
	public static void main (String[] args) {
		Path dir = Path.of("centro", "2026", "exportaciones", "datos.txt");
		Path archivo = dir.resolve("datos.txt");

		try {
			Files.createDirectories(dir);
			Files.writeString(archivo, "Hola Mundo");
			System.out.println("Estructura y fichero creados/actualizados con éxito.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida: " + e.getMessage());
		}
		
	}
	
}
