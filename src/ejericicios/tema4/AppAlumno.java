package ejericicios.tema4;

import java.io.File;
import java.io.RandomAccessFile;

public class AppAlumno {
	
	private static final String FICHERO = "alumnos.dat";

	public static void main(String[] args) {
		AlumnoFicheroDAO dao = new AlumnoFicheroDAO();
		File file = new File(FICHERO);
		
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) { //esto es para que se cierre al final try-with-resources
			
		}catch() {
			
		}
	}
}