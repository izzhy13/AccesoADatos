package ejericicios.bloque1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class P1P2_LibroCSV {

	public static void main(String[] args) {
		Libro libro1 = new Libro("aaa","bbb","ccc",1976);
		System.out.println (libro1.toCSV());
		
		List<Libro> libros= new ArrayList<>();
		libros.add(libro1);
		libros.add(new Libro("Elantris","Brandon Sanderson","Planeta",1968));
		libros.add(new Libro("Harry Potter","J.K","Nova",2002));

		try(BufferedWriter escritor = new BufferedWriter(new FileWriter("libros.csv"))){
			escritor.write("titulo, autor,editorial,anio_publicacion\n");
			for (Libro l: libros) {
				escritor.write(l.toCSV());
				escritor.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}


