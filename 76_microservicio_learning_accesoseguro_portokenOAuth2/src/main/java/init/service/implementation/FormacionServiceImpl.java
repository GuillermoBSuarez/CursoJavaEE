package init.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import init.model.Formacion;
import init.model.TokenResponse;
import init.service.interfaces.FormacionService;
import jakarta.annotation.PostConstruct;

@Service
public class FormacionServiceImpl implements FormacionService {
	
	@Autowired
	RestClient restClient;
	
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
	
	String urlBase = "http://localhost:8500/";
	
	String token;
	
	/* Método que se ejecuta cuando la instancia esté disponible. Evita que en cada llamada de alta se pida un nuevo token:
	.header("Authorization", "Bearer " + token)
	No podemos usar un bloque static porque sólo se ejecutaría una vez, y queremos que se pueda reejecutar cuando caduque
	el token. De ahí el boque try-catch: si caduca se produce una excepción, así que en el catch reejecutamos este método.
	Pero es imprescindible la etiqueta: */
	@PostConstruct
	private void init() {
		token = getToken();
	}

	@Override
	public List<Formacion> catalogo() {
		return Arrays.asList(restClient.get()
									   .uri(urlBase + "cursos")
									   .retrieve()
									   .body(Formacion[].class));
	}

	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {
		return catalogo().stream()
						 .filter( f -> f.getHoras() <= max )
						 .toList();
	}

	@Override
	public void alta(Formacion formacion) {
		try {
			restClient.post()
					  .uri(urlBase + "agregar")
					  .contentType(MediaType.APPLICATION_JSON)
					  .body(formacion)
					  .header("Authorization", "Bearer " + token)
					  .retrieve()
					  .toBodilessEntity();		// ResponseEntity<Void> por si el alta da error.
		}
		catch (Exception ex) {
			/* Excepción por caducidad del token: lo regeneramos y reejecutamos.
			NO ES CORRECTO porque si hay una excepción por otro motivo no está contemplada,
			con lo que reejecutaríamos en bucle para nada y sin saber el error. */
			token = getToken();
			alta(formacion);			
		}
	}
	
	private String getToken() {
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("client_id", client_id);
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", grant_type);

		return restClient.post()
						 .uri(urlAuth)
						 .contentType(MediaType.APPLICATION_FORM_URLENCODED)
						 .body(params)
						 .retrieve()
						 .body(TokenResponse.class)	// devuelve un objeto TokenResponse, ver clase.
						 .getAccess_token();
	}
}
