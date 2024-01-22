package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Libro;
import service.LibrosService;

@WebServlet("/QuitarCarritoController")
public class QuitarCarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* recogemos la posición del libro a eliminar y no su isbn porque vamos a eliminar
		de la lista, no de la tabla, así que usaremos el método remove de List, que pide la
		posición del elemento. Si usásemos isbn tendríamos que buscar el objeto Libro con ese
		isbn en la lista y extraer su posición en la misma para usarlo como argumento. */
		int posicion = Integer.parseInt(request.getParameter("posicion"));
		HttpSession sesion = request.getSession();
		LibrosService service = new LibrosService();
		
		List<Libro> carrito = new ArrayList<>(); 
		if (sesion.getAttribute("carrito") != null) {
			carrito = (List<Libro>) sesion.getAttribute("carrito");
			carrito.remove(posicion);
		}
		sesion.setAttribute("carrito", carrito);

		response.setContentType("application/json");
		response.getWriter().println(new Gson().toJson(carrito));				
	}
}