package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class TablaMultiplicar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><center><table border='1'>");
		for(int i=1;i<=10;i++) {
			out.println("<tr>");
			for(int k=1;k<=10;k++) {
				out.println("<td> "+i*k+" </td>");
			}
			out.println("</tr>");
		}
		out.println("</table></center></body></html>");
	}

}
