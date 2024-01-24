package controllers;

import java.io.IOException;

import dtos.ClienteDTO;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.implementations.ClienteServiceImpl;
import service.interfaces.ClienteService;

@WebServlet("/AutenticarController")
public class AutenticarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	ClienteService service;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteDTO cliente = service.autenticar(request.getParameter("usuario"), request.getParameter("password"));
		request.setAttribute("autenticado", cliente != null);
										   //  true o false
	}
}