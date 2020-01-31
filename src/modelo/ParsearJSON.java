package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modelo.entidad.Pelicula;

public class ParsearJSON {

	private File jsonFile;

	private String json;

	public ParsearJSON(String ruta) {
		this.jsonFile = new File(ruta);
		cargarFichero();
	}

	private void cargarFichero() {
		this.json = "";
		try (BufferedReader br = new BufferedReader(new FileReader(jsonFile));) {
			String texto;
			while ((texto = br.readLine()) != null) {
				json += texto;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addFavoritos(Pelicula peli) {
		JSONArray root = null;
		if (json.equals(""))
			root = new JSONArray();
		else
			root = new JSONArray(json);
		JSONObject peliJSON = new JSONObject();
		peliJSON.put("titulo", peli.getTitulo());
		peliJSON.put("url", peli.getUrlImg());
		peliJSON.put("director", peli.getDirector());
		peliJSON.put("sinopsis", peli.getSinopsis());
		peliJSON.put("nota", peli.getNota());
		JSONArray actores = new JSONArray();
		for (String estring : peli.getListaActores()) {
			actores.put(estring);
		}
		peliJSON.put("actores", actores);
		root.put(peliJSON);
		try (FileWriter fw = new FileWriter(jsonFile);) {
			fw.write(root.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Pelicula> parsearJSON() throws JSONException {
		List<Pelicula> lista = new ArrayList<Pelicula>();
		JSONArray root = new JSONArray(json);
		for (int i = 0; i < root.length(); i++) {
			JSONObject peli = root.getJSONObject(i);
			String director = peli.getString("director");
			String titulo = peli.getString("titulo");
			String nota = peli.getString("nota");
			String urlImg = peli.getString("url");
			String sinopsis = peli.getString("sinopsis");
			List<String> listaActores = new ArrayList<String>();
			JSONArray actores = peli.getJSONArray("actores");
			for (int j = 0; j < actores.length(); j++) {
				listaActores.add(actores.getString(j));
			}
			lista.add(new Pelicula(urlImg, titulo, nota, sinopsis, director, listaActores));
		}
		return lista;
	}

	public List<Pelicula> eliminarFav(String tituloPeli) {
		List<Pelicula> listaPelis = new ArrayList<Pelicula>();
		JSONArray root = new JSONArray(json);
		for (int i = 0; i < root.length(); i++) {
			JSONObject peli = root.getJSONObject(i);
			if (peli.getString("titulo").equals(tituloPeli))
				root.remove(i);
			else {
				String director = peli.getString("director");
				String titulo = peli.getString("titulo");
				String nota = peli.getString("nota");
				String urlImg = peli.getString("url");
				String sinopsis = peli.getString("sinopsis");
				List<String> listaActores = new ArrayList<String>();
				JSONArray actores = peli.getJSONArray("actores");
				for (int j = 0; j < actores.length(); j++) {
					listaActores.add(actores.getString(i));
				}
				listaPelis.add(new Pelicula(urlImg, titulo, nota, sinopsis, director, listaActores));
			}
		}
		try (FileWriter fw = new FileWriter(jsonFile)) {
			fw.write(root.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaPelis;
	}
}
