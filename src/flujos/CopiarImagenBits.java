package flujos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopiarImagenBits {

	public static void main(String[] args) {
		Path rutaOrigen = Path.of("imagenOriginal.jpeg");
		Path rutaDestino = Path.of("imagenCopia.jpeg");

		try (InputStream in = Files.newInputStream(rutaOrigen);
				OutputStream out = Files.newOutputStream(rutaDestino)) {

			// Creamos un array (búfer)
			byte [] buffer = new byte[8192];
			int bytesLeidos;
			int numBloque = 0;
			
            System.out.println("=== Leyendo y copiando archivo en bloques de 8192 bytes ===");
            
            while ((bytesLeidos = in.read(buffer)) != -1 ) {
            	numBloque++;
            	
            	String fragmento = new String(buffer, 0, bytesLeidos, StandardCharsets.UTF_8);
            	
            	 System.out.printf("Bloque #%d | Bytes leídos: %2d | Contenido: \"%s\"%n", 
                         numBloque, bytesLeidos, fragmento);

                out.write(buffer, 0, bytesLeidos);
            }
            
            System.out.println("Copia completada con éxito en: " + rutaDestino.toAbsolutePath());


		} catch (IOException e) {
			System.err.println("Error durante la operación de E/S: " + e.getMessage());
		}

	}
}