package service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Formacion;
import service.interfaces.FormacionService;

@Service
public class FormacionServiceImpl implements FormacionService {
	
	@Autowired
	RestClient restClient;		// Para conectar con el servicio del buscador de cursos
	
	String urlBase = "http://localhost:8080/59_cursos_webservices/";

	@Override
	public List<Formacion> catalogo() {
		return Arrays.asList(restClient.get()
									   .uri(urlBase + "cursos")
									   .retrieve()
									   .body(Formacion[].class));
		/* No se puede indicar la class de una List, que pide body, pero sí de un ArrayList.
		Pero el método debe devolver una lista, y para convertir un Array en List, Arrays.asList */
	}

	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {
		/* En el webservice no hay método de "dame el curso de duración máxima",
		así que pedimos todos y filtramos. */
		return catalogo().stream()
						 .filter( f -> f.getHoras() <= max )
						 .toList();
	}

	@Override
	public void alta(Formacion formacion) {
		restClient.post()
				  .uri(urlBase + "agregar")
				  .contentType(MediaType.APPLICATION_JSON)	// No admite el liberal "application/Json"
				  .body(formacion)
				  .retrieve();
		/* Ojo, envía un objeto Formación y el webservice necesita un objeto Curso, pues
		los atributos de uno y otro se llaman distinto: duracion vs horas, por ejemplo. */
	}
}
