package controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import dtos.LibroDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.LibrosService;

@WebServlet("/LibrosTemaController")
public class LibrosTemaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LibroDTO> libros = new LibrosService().getLibrosTema(Integer.parseInt(request.getParameter("idTema")));
		
		response.setContentType("application/json");
		response.getWriter().println(new Gson().toJson(libros));				
	}
}