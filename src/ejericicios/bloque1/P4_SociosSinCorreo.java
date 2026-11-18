package ejericicios.bloque1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import repaso.docs.AccesoDatosException;

public class P4_SociosSinCorreo {

	public static void main(String[] args) throws AccesoDatosException, IOException {
		Path ruta = Path.of("datos", "socios.txt");
		Files.createDirectories(ruta.getParent());

		List<Socio> listaSocios = new ArrayList<>();
		int sociosSinCorreo = 0;

		try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            bw.write("1;Ana Pérez;2023-01-15;ana@email.com");
            bw.newLine();
            bw.write("2;Carlos Gómez;2023-02-20;"); // Campo vacío (tiene el ';')
            bw.newLine();
            bw.write("3;María López;2023-03-10");    // Campo ausente (no tiene el último ';')
            bw.newLine();
            bw.write("4;Luis Torres;2023-04-05;luis@email.com");
            bw.newLine();
        } catch (IOException e) {
            throw new AccesoDatosException("Error al escribir el fichero de socios en: " + ruta, e);
        }
		
		try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
			String linea;

			while ((linea = br.readLine()) != null) {
				// El -1 es clave para no perder los campos vacíos del final (si el correo no está)
				String[] partes = linea.split(";", -1);

				String carne = partes[0];
				String nombre = partes[1];
				String fechaAlta = partes[2];

				// Controlamos si el correo existe en la línea o viene vacío
				String correo = (partes.length >= 4) ? partes[3].trim() : "";

				// Creamos el objeto socio y lo añadimos a la lista
				Socio socio = new Socio(carne, nombre, fechaAlta, correo);
				listaSocios.add(socio);

				// Comprobamos si no tiene correo para el contador
				if (socio.noTieneCorreo()) {
					sociosSinCorreo++;
				}
			}

		} catch (IOException e) {
			throw new AccesoDatosException("Error al leer el fichero de socios: " + ruta, e);
		}

		// Si obligatoriamente te piden un ARRAY en lugar de una Lista:
		// Socio[] arraySocios = listaSocios.toArray(new Socio[0]);

		System.out.println("--- Resultados P4 ---");
		System.out.println("Total de socios leídos: " + listaSocios.size());
		System.out.println("Socios que NO tienen correo: " + sociosSinCorreo);
	}
}