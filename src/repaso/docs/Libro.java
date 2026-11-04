package repaso.docs;

import java.time.LocalDate;
import java.util.Objects;

public class Libro {
	private String titulo;
	private String autor;
	private String codigo;
	private int anio_publi; 

	public Libro(String titulo, String autor, String codigo, LocalDate anio_publi) {
		if ( anio_publi == null || anio_publi.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Fecha de publicación en el futuro");
		}

		if (codigo == null || codigo.trim().length() != 13) {
			throw new IllegalArgumentException("EL codigo debe de tener trece caracteres");
		}

		this.titulo = titulo;
		this.autor = autor;
		this.codigo = codigo;
		this.anio_publi = anio_publi.getYear();
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(codigo, other.codigo);
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getAnio_publi() {
		return anio_publi;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", codigo=" + codigo + ", anio_publi=" + anio_publi
				+ "]";
	}

}
