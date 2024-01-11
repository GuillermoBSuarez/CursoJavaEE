package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class TablaNumero extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numero=Integer.parseInt(request.getParameter("numero"));
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><center>");
		out.println("<h1>Tabla del "+numero+"</h1>");
		out.println("<table border='1'>");
		for(int i=1;i<=10;i++) {
			out.println("<tr>");
				out.println("<td> "+i+"X"+numero+" </td><td>"+i*numero+"</td>");
			out.println("</tr>");
		}
		out.println("</table><br><a href='numero.html'>volver</a></center></body></html>");
	}

}
