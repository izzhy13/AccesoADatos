package ejericicios.bloque1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import repaso.docs.AccesoDatosException;

public class P1P2P3_LibroCSV {

	public static void main(String[] args) throws IOException  {
		Libro libro1 = new Libro("aaa","bbb","ccc",1976);
		System.out.println (libro1.toCSV());
		Path ruta = Path.of("datos","libros.csv");
		Files.createDirectories(ruta.getParent());

		List<Libro> libros= new ArrayList<>();
		libros.add(libro1);
		libros.add(new Libro("Elantris","Brandon Sanderson","Planeta",1968));
		libros.add(new Libro("Harry Potter","J.K","Nova",2002));

		try(BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)){

			bw.write("titulo, autor,editorial,anio_publicacion\n");
			for (Libro l: libros) {
				bw.write(l.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Libro> librosRecuperados = new ArrayList<>();

		try(BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)){
			// 1. Leer y descartar la primera línea (la cabecera)
			String cabecera = br.readLine();

			String linea;

			while ((linea = br.readLine()) != null) {
				librosRecuperados.add(convertir(linea));
			}

		} catch (IOException e) {
			throw new AccesoDatosException("No se pudo guardar el fichero en la ruta: " + ruta, e);
		}
		
		System.out.println("--- Comprobación de P3 ---");
        System.out.println("Libros recuperados del fichero: " + librosRecuperados.size());

	}	

	private static Libro convertir(String linea) {
		// PASO 1: Cortar la línea por el punto y coma
		String[] partes = linea.split(",");
		String titulo = partes[0];
		String autor = partes[1];
		String editorial = partes[2];
		int anio = Integer.parseInt(partes[3]);

		return new Libro(titulo,autor,editorial,anio);
	}

}




