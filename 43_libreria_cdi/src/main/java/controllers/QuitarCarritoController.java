package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dtos.LibroDTO;
import jakarta.inject.Inject;
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
    
	@Inject
	LibrosService service;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int posicion = Integer.parseInt(request.getParameter("posicion"));
		HttpSession sesion = request.getSession();
		
		List<LibroDTO> carrito = new ArrayList<>(); 
		if (sesion.getAttribute("carrito") != null) {
			carrito = (List<LibroDTO>) sesion.getAttribute("carrito");
			carrito.remove(posicion);
		}
		sesion.setAttribute("carrito", carrito);

		response.setContentType("application/json");
		response.getWriter().println(new Gson().toJson(carrito));				
	}
}