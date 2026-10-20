package ejericicios.tema4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppAlumno {

	private static Scanner sc = new Scanner(System.in);

	public static void alta10Alumnos(RandomAccessFile raf, AlumnoFicheroDAO dao) throws IOException {
		//Vamos a crear una lista, por trabajar con ellas, se podrian poner juntos
		List <Alumno> alumnos = new ArrayList<>();

		for (int i=1; i <=10; i++) {
			alumnos.add(new Alumno(i, "A1234" + i, "Juan" + i, "Gomez Puente", 
					LocalDate.of(2022, 11, 10), "correo@edu.com"));
		}

		//para meterlos enel fichero recorremos la lista
		for (Alumno a : alumnos ) {
			dao.escribir(raf, a);
		}

		//version buena
		//for (int i=1; i <=10; i++) {
		//	dao.escribir(raf, new Alumno(i, "A1234" + i, "Juan" + i, "Gomez Puente", 
		//			LocalDate.of(2022, 11, 10), "correo@edu.com"));
		//}
	}

	public static void consultarEstadisticas(RandomAccessFile raf) throws IOException {
		System.out.println("El registro ocupa " + FormatoAlumno.TAMANO);
		System.out.println("El fichero ocupa " + raf.length());
		System.out.println("Estamos en la posicion " + raf.getFilePointer());
	}

	public static void baja1Alumno(RandomAccessFile raf,AlumnoFicheroDAO dao) throws IOException {
		System.out.println("Que alumno quieres borrar?");
		int numAlumno = sc.nextInt();
		dao.borrarAlumno(raf, numAlumno);

	}

	public static void listarAlumnos(RandomAccessFile raf,AlumnoFicheroDAO dao) throws IOException {
		//vamos a ver cuantos registros tenemos
		long numRegistros = (raf.length()/FormatoAlumno.TAMANO);
		raf.seek(0);
		for (int i = 1; i <= numRegistros; i++) {
			Optional <Alumno> a = dao.leer(raf);
			if(a.isPresent()) {
				System.out.println(a.toString());
			} else {
				//si no hemos leido el registro entero, avanzo a la sigueinte posición
				//para que no se nos lie con la lectura del puntero
				raf.seek(FormatoAlumno.TAMANO*i);
			}
		}
	}

	public static void main(String[] args) { //esto en la app real no habría qu hacerlo
		AlumnoFicheroDAO dao = new AlumnoFicheroDAO();
		try (RandomAccessFile raf = new RandomAccessFile("fichero_alumnos", "rw")) { //esto es para que se cierre al final try-with-resources

			int opcion;

			do {
				System.out.println("Menu");
				System.out.println("1. Dar de alta 10 alumnos");
				System.out.println("2. Dar de baja 1 alumno");
				System.out.println("3. Dar de alta 1 alumno");
				System.out.println("4. Listar todos los alumnos");
				System.out.println("5. Consultar estadisticas");
				System.out.println("6. Mover el puntero");
				System.out.println("7. Eliminar todos los alumnos");
				System.out.println("8. Salir");
				opcion = sc.nextInt();

				switch(opcion) {
				case 1 -> alta10Alumnos(raf,dao);
				case 2 -> baja1Alumno(raf, dao);
				case 3 -> dao.escribir(raf, new Alumno(50, "B1222", "Alumno", "Creado a mano", 
						LocalDate.of(2026, 10, 20), null));
				case 4 -> listarAlumnos(raf,dao);
				case 5 -> consultarEstadisticas(raf);
				default -> System.out.println("Opcion no valida");
				}

			} while (opcion!=8);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}