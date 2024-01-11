package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("operation");
		String urlView = "";					// a donde enviaremos la ejecución
		switch (op) {
			case "doAlta":
				request.getRequestDispatcher("AltaController").include(request, response);
				/* include no transfiere el control sino lo "presta", así que al final
				de la ejecución del alta la ejecución vuelve aquí */
				urlView = "inicio.html";
				break;
			case "doBuscarProducto":
				request.getRequestDispatcher("BuscarProductoController").include(request, response);
				urlView="producto.jsp";
				break;
			case "toAlta":
				urlView = "alta.html";
				break;
			case "toBuscarProducto":
				urlView = "buscarProducto.html";
				break;
			case "toInicio":
				urlView = "inicio.html";
				break;
		}
		request.getRequestDispatcher(urlView).forward(request, response);		
	}
}