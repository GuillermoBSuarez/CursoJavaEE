package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import service.ProductosService;

@WebServlet("/BuscarProductoController")
public class BuscarProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductosService service = new ProductosService();
		Producto pr = service.buscarProducto(Integer.parseInt(request.getParameter("idProducto")));
		request.setAttribute("producto", pr);	// para que lo pueda ver la página JSP que lo mostrará.
	}
}