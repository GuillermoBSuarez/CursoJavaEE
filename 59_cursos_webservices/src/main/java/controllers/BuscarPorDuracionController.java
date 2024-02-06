package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curso;
import service.interfaces.CursoService;
import service.interfaces.CursoServiceImpl;

@WebServlet("/BuscarPorDuracionController")
public class BuscarPorDuracionController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursoService service = new CursoServiceImpl();
		List<Curso> cursos = service.buscarPorDuracion(
				Integer.parseInt(request.getParameter("duracionMin")),
				Integer.parseInt(request.getParameter("duracionMax")) );
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.println(gson.toJson(cursos));
	}
}