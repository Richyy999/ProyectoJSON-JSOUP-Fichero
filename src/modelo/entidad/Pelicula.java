package modelo.entidad;

import java.util.List;

public class Pelicula {

	private String urlImg;
	private String titulo;
	private String nota;
	private String sinopsis;
	private String director;
	private List<String> listaActores;
	
	public Pelicula(String urlImg, String titulo, String nota, String sinopsis, String director,
			List<String> listaActores) {
		this.urlImg = urlImg;
		this.titulo = titulo;
		this.nota = nota;
		this.sinopsis = sinopsis;
		this.director = director;
		this.listaActores = listaActores;
	}
}
