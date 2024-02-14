package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Libro;
import init.service.interfaces.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	RestClient restClient;
	
	@Value("${app.user}")
	String user;
	@Value("${app.pass}")
	String pass;
	String urlBase = "http://localhost:9000/";
	
	@Override
	public List<String> tematicas() {
		return Arrays.asList(restClient.get()
									   .uri(urlBase + "tematicas")
									   .retrieve()
									   .body(String[].class));
	}
	
	@Override
	public List<Libro> libros(String tematica) {
		return Arrays.asList(restClient.get()
								   	   .uri(urlBase + "libros")
								   	   .header("Authorization", "basic "+getBase64(user, pass))
								   	   .retrieve()
								   	   .body(Libro[].class))
				.stream()
				.filter( l -> l.getTematica().equals(tematica) )
				.toList();
	}
	
	private String getBase64(String user, String pass) {
		String cadena = user + ":" + pass;
		return Base64.getEncoder().encodeToString(cadena.getBytes());
	}
}