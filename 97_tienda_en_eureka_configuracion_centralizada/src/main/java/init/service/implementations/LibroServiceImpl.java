package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.model.Libro;
import init.model.TokenResponse;
import init.service.interfaces.LibroService;
import jakarta.annotation.PostConstruct;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired()
	@Qualifier("RibbonClient")
	RestClient restRibbonClient;
	
	@Autowired()
	@Qualifier("NotRibbonClient")
	RestClient restNotRibbonClient;
	
	@Value("${app.urlAuth}")
	String urlAuth;
	@Value("${app.username}")
	String username;
	@Value("${app.password}")
	String password;
	@Value("${app.client_id}")
	String client_id;
	@Value("${app.grant_type}")
	String grant_type;
	
	String urlBase = "http://libreria/";
	
	String token;
	
	@PostConstruct
	private void init() {
		token = getToken();
	}
	
	@Override
	public List<String> tematicas() {
		return Arrays.asList(restRibbonClient.get()		// RestClient con Ribbon: va a Eureka
									 		 .uri(urlBase + "tematicas")
									 		 .retrieve()
									 		 .body(String[].class));
	}
	
	@Override
	public List<Libro> librosTematica(String tematica) {
		try {
			return Arrays.asList(restRibbonClient.get()	// RestClient con Ribbon: va a Eureka
									   	   		 .uri(urlBase + "libros")
									   	   		 .header("Authorization", "Bearer " + token)
									   	   		 .retrieve()
									   	   		 .body(Libro[].class))
						 .stream()
						 .filter(b->b.getTematica().equals(tematica))
						 .toList();
		} catch (Exception es) {
			token = getToken();
			return librosTematica(tematica);
		}
	}
	
	private String getToken() {
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("client_id", client_id);
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", grant_type);

		return restNotRibbonClient.post()			// RestClient sin Ribbon: no va a Eureka
						 		  .uri(urlAuth)
						 		  .contentType(MediaType.APPLICATION_FORM_URLENCODED)
						 		  .body(params)
						 		  .retrieve()
						 		  .body(TokenResponse.class)
						 		  .getAccess_token();
	}
}