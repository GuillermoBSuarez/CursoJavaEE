package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class SaludoPersonalizado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("nombre");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><center>");
		for(int i=1;i<=6;i++) {
			out.println("<h"+i+">Hola "+nombre+"</h"+i+">");
		}
		out.println("</center></body></html>");
		out.close();
	}

}
