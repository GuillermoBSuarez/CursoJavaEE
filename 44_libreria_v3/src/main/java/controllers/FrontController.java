package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getParameter("operation")) {
		case "toLogin":
			request.getRequestDispatcher("login.html" ).forward(request, response);
			break;
		case "doAutenticar":
			request.getRequestDispatcher("AutenticarController").include(request, response);
			request.getRequestDispatcher( (Boolean) request.getAttribute("autenticado") ?
				"FrontController?operation=doTemas" :
				"error.jsp" ).forward(request, response);
			break;
		case "toNuevo":
			request.getRequestDispatcher("nuevo.html" ).forward(request, response);
			break;
		case "doAlta":
			request.getRequestDispatcher("AltaController").include(request, response);
			request.getRequestDispatcher("login.html" ).forward(request, response);
			break;
		case "doTemas":
			request.getRequestDispatcher("TemasController").include(request, response);
			request.getRequestDispatcher("visor.jsp").forward(request, response);
			break;
		case "doLibrosTema":
			request.getRequestDispatcher("LibrosTemaController").forward(request, response);
			return;
		case "doAgregar":
			request.getRequestDispatcher("AgregarController").forward(request, response);
			return;
		case "doQuitar":
			request.getRequestDispatcher("QuitarCarritoController").forward(request, response);
			return;
		}
	}
}