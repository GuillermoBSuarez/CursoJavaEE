package controllers;

import dtos.ClienteDTO;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.ClienteService;

@WebServlet("/AltaController")
public class AltaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Inject
    ClienteService service;
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        service.altaCliente(new ClienteDTO(
        		request.getParameter("usuario"),
        		request.getParameter("password"),
        		request.getParameter("email"),
        		Integer.parseInt(request.getParameter("telefono"))));
    }

}
