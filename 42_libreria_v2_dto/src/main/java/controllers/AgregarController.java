package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dtos.LibroDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Libro;
import service.LibrosService;

@WebServlet("/AgregarController")
public class AgregarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		HttpSession sesion = request.getSession();
		LibrosService service = new LibrosService();
		
		// los controllers YA NO CONOCEN LAS ENTIDADES, service devuelve DTOs
		LibroDTO libro = service.getLibro(isbn);

		List<LibroDTO> carrito = new ArrayList<>(); 
		if (sesion.getAttribute("carrito") != null) {
			carrito = (List<LibroDTO>) sesion.getAttribute("carrito");
		}
		carrito.add(libro);
		sesion.setAttribute("carrito", carrito);

		response.setContentType("application/json");
		response.getWriter().println(new Gson().toJson(carrito));				
	}
}