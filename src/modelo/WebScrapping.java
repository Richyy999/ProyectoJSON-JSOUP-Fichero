package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import modelo.entidad.Pelicula;

public class WebScrapping {

	Document root;

	public WebScrapping() {
		try {
			root = Jsoup.connect("https://www.filmaffinity.com/es/topcat_new_th_es.html").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Pelicula> scrapping() {
		List<Pelicula> listaFilms = new ArrayList<Pelicula>();
		Elements peliculas = root.select(".top-movie");
		for (int i = 0; i < peliculas.size(); i++) {
			Element pelicula = peliculas.get(i);

			// Escojo imagen
			Element poster = pelicula.selectFirst(".mc-poster>a>img");
			String urlImg = poster.attr("src");

			// Cojo el titulo
			Element title = pelicula.selectFirst(".mc-right>h3>a");
			String titulo = title.text();

			// Cojo puntuacion
			Element mark = pelicula.selectFirst(".avg-rating");
			String puntuacion = mark.text();

			// Cojo director
			Element direc = pelicula.selectFirst(".director>div>span>a");
			String director = direc.text();

			// Cojo actores
			List<String> actors = new ArrayList<>();
			Elements cast = pelicula.select(".cast>div>span>a");
			for (int j = 0; j < cast.size(); j++) {
				String actor = cast.get(j).text();
				actors.add(actor);
			}

			// Coger la sinopsis
			Element sinop = pelicula.selectFirst(".synop-text");
			String sinopsis = sinop.text();

			Pelicula peli = new Pelicula(urlImg, titulo, puntuacion, sinopsis, director, actors);
			listaFilms.add(peli);
		}
		return listaFilms;
	}
}