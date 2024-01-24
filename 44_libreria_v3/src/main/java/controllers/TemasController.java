package controllers;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.LibrosService;

@WebServlet("/TemasController")
public class TemasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	LibrosService service;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("temas", service.getTemas());
	}
}
