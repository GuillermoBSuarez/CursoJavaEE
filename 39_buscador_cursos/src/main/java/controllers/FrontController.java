package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("operation");
		
		switch (op) {
			case "doBuscarPorPrecio":
				request.getRequestDispatcher("BuscarPorPrecioController").forward(request, response);
				return;
			case "doBuscarPorDuracion":
				request.getRequestDispatcher("BuscarPorDuracionController").forward(request, response);
				return;
		}
	}
}