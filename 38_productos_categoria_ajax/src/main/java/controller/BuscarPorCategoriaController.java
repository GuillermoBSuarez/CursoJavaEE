package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import service.ProductosService;

@WebServlet("/BuscarPorCategoriaController")
public class BuscarPorCategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductosService service = new ProductosService();
		List<Producto> productos = service.buscarPorCategoria(request.getParameter("categoria"));
		
		// cambiamos la versión del 28 para, en vez de guardarlo en atributo de sesión,
		// enviárselo directamente al cliente.
		PrintWriter out = response.getWriter();
		response.setContentType("application/json"); // buscar en Google "json mime type" para saber qué poner

		// necesitamos una instancia de la librería Gson
		Gson gson = new Gson();
		out.println(gson.toJson(productos));
	}
}