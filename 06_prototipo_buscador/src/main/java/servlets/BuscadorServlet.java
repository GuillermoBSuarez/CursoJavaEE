package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Resultado;
import service.BuscadorService;

@WebServlet("/BuscadorServlet")
public class BuscadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuscadorService buscadorService=new BuscadorService();
		List<Resultado> resultados=buscadorService.buscar(request.getParameter("tematica"));
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><center>");
		System.out.println(resultados.size());
		for(Resultado r:resultados) {
			out.println("<h2><a href='"+r.getUrl()+"'>"+r.getUrl()+"</a></h2>");
			out.println("<h4>"+r.getDescripcion()+"</h4><hr>");
		}
		out.println("</center></body></html>");
	}

	
	

}
