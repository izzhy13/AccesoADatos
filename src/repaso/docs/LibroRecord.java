package repaso.docs;

import java.time.LocalDate;

public record LibroRecord(String titulo, String autor, String codigo, int anio_publi) { 
	//mas corto, inmutable(son solo lectura no se puden cambiar, habría que crear otros), no pueden heredar, pero si interfacear
	// se usan cuando solo necesitas transportar o guardar datos que no van a cambiar
	
	public LibroRecord {
		if (anio_publi > LocalDate.now().getYear()) {
		    throw new IllegalArgumentException("Fecha de publicación en el futuro");
		}

		if (codigo == null || codigo.trim().length() != 13) {
			throw new IllegalArgumentException("EL codigo debe de tener trece caracteres");
		}
	}
}