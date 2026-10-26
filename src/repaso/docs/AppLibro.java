package repaso.docs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AppLibro {
	
	//public void 

	public static void main(String[] args) {
		Libro l1 = new Libro("Elantris","Brandon Sanderson","123456789ABCD",LocalDate.of(2011, 7, 1));
		Libro l2 = new Libro("Acotar","Sarah J Mass","123456789ABCD",LocalDate.of(2018, 5, 12));

		System.out.println("Misma referencia" + (l1==l2));
		System.out.println("Es igual el codigo" + (l1.equals(l2)));

		Set <Libro> listaLibros1 = new HashSet<>();
		listaLibros1.add(l1);
		listaLibros1.add(l2);
		
		// Queda 1 elemento porque el hashCode coincide (caen en el mismo cajón) y el equals confirma que son iguales.
		System.out.println("Elementos HashSet " + listaLibros1.size());
		
		//Si comentamos el hashCode(): El HashSet usa el hashCode por defecto por lo que caen en 
		//cajones distintos, el equals no llega a ejecutarse y se guardan los 2 elementos 
		
		List<Libro> listaLibros2 = new ArrayList();
		listaLibros2.add(new Libro("Acotar","Sarah J Mass","123456789ABCD",LocalDate.of(2018, 5, 12)));
		listaLibros2.add(new Libro("Cien años de soledad","Gabriel García Márquez","ABCD123456789",LocalDate.of(2008, 4, 2)));
		listaLibros2.add(new Libro("Elantris","Brandon Sanderson","987654321ABCD",LocalDate.of(2024, 12, 5)));
		listaLibros2.add(new Libro("El Familiar","Leight Bardugo","CDBA123456789",LocalDate.of(2015, 10, 10)));
		listaLibros2.add(new Libro("Mistborn","Brandon Sanderson","123456789ABCD",LocalDate.of(2000, 1, 9)));

		// publicados después de 2015 ordenados por título
		List<Libro> publicadosPost2015 = listaLibros2.stream()
				.filter(l -> l.getAnio_publi() > 2015)
				.sorted(Comparator.comparing(Libro::getTitulo))
				.toList();
		
		//  los títulos de un autor concreto
		List<String> librosDeBrandon = listaLibros2.stream() //tiene que ser String ya que los titulos son strings
				.filter(a -> a.getAutor().equals("Brandon Sanderson"))
				.map((Libro::getTitulo)) //usamos map porque tranformamos el objeto Libro en String: su titulo
				.toList();
		
		//  el num de libros de un autor concreto
		Map<String, Long> numLibros = listaLibros2.stream() //clave valor
				.collect(Collectors.groupingBy(
				        Libro::getAutor, 
				        Collectors.counting()
				    ));
		
		// el libro mas antiguo
		Libro libroMasAntiguo = listaLibros2.stream()
				.min(Comparator.comparing(Libro::getAnio_publi))
				.orElse(null) //si la lista esta vacia devuelve null
				;
		
		System.out.println(publicadosPost2015.toString());
		System.out.println(librosDeBrandon);
		System.out.println(numLibros);
		System.out.println(libroMasAntiguo);
		
	}

}
