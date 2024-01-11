package servlets;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EntradaServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Comprobar si llega la cookie "user"
		/*String usuario=null;
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie ck:cookies) {
				if(ck.getName().equals("usuario")) {
					usuario=ck.getValue();
					break;
				}
			}
		}*/
		String usuario=Arrays.stream(request.getCookies()!=null?request.getCookies():new Cookie[0])
					.filter(c->c.getName().equals("usuario"))
					.findFirst()
					.orElse(new Cookie("usuario",null))
					.getValue();
		
		if(usuario==null) {
			request.getRequestDispatcher("login.html").forward(request, response);
		}else {
			request.getRequestDispatcher("correcto.jsp?usuario="+usuario).forward(request, response);
		}
	}

}
