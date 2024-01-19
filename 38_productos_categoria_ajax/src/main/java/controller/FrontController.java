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
			case "doBuscarPorCategoria":
				request.getRequestDispatcher("BuscarPorCategoriaController").forward(request, response);;
				return;
		}
		// request.getRequestDispatcher(urlView).forward(request, response);		
	}
}