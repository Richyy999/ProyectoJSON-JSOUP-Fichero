package modelo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import modelo.entidad.Pelicula;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("listaPelis") == null) {
			WebScrapping web = new WebScrapping();
			List<Pelicula> listaPelis = web.scrapping();
			String tabla = GenerarHTML.crearTabla(listaPelis);
			session.setAttribute("listaPelis", listaPelis);
			session.setAttribute("tabla", tabla);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			ParsearJSON parse = new ParsearJSON();
			try {
				int index = Integer.parseInt(request.getParameter("index"));
				List<Pelicula> lista = (List<Pelicula>) session.getAttribute("listaPelis");
				Pelicula peli = lista.get(index);
				parse.addFavoritos(peli);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				String irFav = request.getParameter("irFav");
				if (irFav != null)
					irFav(request, parse);
				else {
					String titulo = request.getParameter("titulo");
					eliminarFav(request, parse, titulo);
				}
				request.getRequestDispatcher("favoritos.jsp").forward(request, response);
			}
		}
	}

	private void eliminarFav(HttpServletRequest request, ParsearJSON parse, String titulo) {
		List<Pelicula> listaPelis = parse.eliminarFav(titulo);
		String tabla = GenerarHTML.crearTablaFav(listaPelis);
		request.setAttribute("fav", tabla);
	}

	private void irFav(HttpServletRequest request, ParsearJSON parse) throws ServletException {
		try {
			List<Pelicula> listaPelis = parse.parsearJSON();
			String tabla = GenerarHTML.crearTablaFav(listaPelis);
			request.setAttribute("fav", tabla);
		} catch (JSONException e) {
			request.setAttribute("fav", "No hay favoritos");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
