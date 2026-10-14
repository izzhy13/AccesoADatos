package ejericicios.bloque1;

public class Libro {
	private String titulo;
	private String autor;
	private String editorial;
	private int anio_publicacion;


	public Libro(String nombre, String autor, String editorial, int anio_publicacion) {
		this.titulo = nombre;
		this.autor = autor;
		this.editorial = editorial;
		this.anio_publicacion = anio_publicacion;
	}

	public String getNombre() {
		return titulo;
	}


	public String getAutor() {
		return autor;
	}


	public String getEditorial() {
		return editorial;
	}


	public int getAnio_publicacion() {
		return anio_publicacion;
	}


	public String toCSV() {
		//return titulo + "," + autor + "," + editorial + "," + anio_publicacion;
		return String.format("%s,%s,%s,%d", titulo, autor, editorial, anio_publicacion);
	}

}