package intercepting;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FiltroLogin extends HttpFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Si el usar está autenticado existirá el atributo de sesión usuario, y le dejamos pasar.
		// Si no, a la página de login.
		HttpSession sesion = ((HttpServletRequest) request).getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		if (usuario != null) {
			// pass the request along the filter chain
			chain.doFilter(request, response);	
		} else {
			/* Si el usuario no es nulo es que ya se logó. Pero OJO, si está intentando logarse aún no existe este atributo,
			por lo que no le deja pasar sino que le envía a Login, pero como no existe el atributo de sesión porque aún no está
			logado, le reenvía a login.html, así que entra en bucle. Hay que poner un segundo filtro:
			si existe el usuario O si la dirección de destino es login. En esos caso le dejamos pasar. */
			String param = ((HttpServletRequest) request).getParameter("operation");
			if (param != null && param.equals("doLogin")) {
				// Primero comprobamos que no sea nulo porque si es nulo el equals da una excepción.
				chain.doFilter(request, response);
			}
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}