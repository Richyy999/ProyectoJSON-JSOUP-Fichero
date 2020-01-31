package modelo;

import java.util.List;

import modelo.entidad.Pelicula;

public class GenerarHTML {

	public static String crearTabla(List<Pelicula> lista) {
		String tabla = "<table border=\"1 solid black\">";
		for (int i = 0; i < lista.size(); i++) {
			tabla += lista.get(i).toHTML() + "<td><a href=\"Servlet?index=" + i
					+ "\"><div class=\"boton\">Favoritos</div></a></td></tr>";
		}
		return tabla;
	}

	public static String crearTablaFav(List<Pelicula> listaPelis) {
		if (listaPelis.size() == 0)
			return "No hay Favoritos";
		else {
			String tabla = "<table border=\"1 solid black\">";
			for (int i = 0; i < listaPelis.size(); i++) {
				tabla += listaPelis.get(i).toHTML() + "<td><a href=\"Servlet?titulo="
						+ listaPelis.get(i).getTitulo() + "\"><div class=\"boton\">Eliminar</div></a></td></tr>";
			}
			return tabla;
		}
	}
}
