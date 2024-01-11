package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsuariosService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuariosService service = new UsuariosService();
		
		// guardamos el resultado de la evaluación del usuario en una variable...
		boolean result = service.autenticar(request.getParameter("usuario"),
								 			request.getParameter("password"));
		// ... que añadimos como atributo de petición.
		request.setAttribute("autenticado", result);
		// Si el usuario está autenticado lo guardamos en un atributo de sesión
		if (result)
			request.getSession().setAttribute("usuario",  request.getParameter("usuario"));
	}
}