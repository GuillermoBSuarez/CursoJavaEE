package controllers;

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
		switch (request.getParameter("operation")) {
		case "doTemas":
			request.getRequestDispatcher("TemasController").include(request, response);
			request.getRequestDispatcher("visor.jsp").forward(request, response);
			break;
		/*
		PRIMERA Y ERRÓNEA VERSIÓN: no puede ser un toTemas con un simple link porque no ejecutaría nada
		al ir a la página, y debe cargar el list de temas, así que lo enviamos al Controller para que la cargue
		y luego a la página con dicha lista de parámetro:
		case "toTemas":
			request.getRequestDispatcher("temas.html").forward(request, response);
			break;
		*/
		}
	}
}