package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.dto.ReservaDTO;
import init.model.Hotel;
import init.model.TokenResponse;
import init.model.Vuelo;
import init.service.dao.ReservaDAO;
import init.service.interfaces.ReservaService;
import init.service.mapper.Mapper;
import jakarta.annotation.PostConstruct;

@Service
public class ReservaServiceImpl implements ReservaService {
	
	@Autowired
	ReservaDAO dao;
	@Autowired
	RestClient restClient;
	@Autowired
	Mapper mapper;
	
	// Parámetros para solicitar el token a KeyCloak
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
	// Variable donde grabar la respuesta de KeyCloak
	String token;

	String urlHoteles = "http://localhost:8001/";
	Hotel hotel;
	String urlVuelos = "http://localhost:8002/";
	Vuelo vuelo;
	
	@PostConstruct
	private void init() {
		token = getToken();
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
						 .body(TokenResponse.class)
						 .getAccess_token();
	}
	
	private Hotel getHotel(int IdHotel) {
		return restClient.get()
						 .uri(urlHoteles + "hotel/" + IdHotel)
						 .retrieve()
						 .body(Hotel.class);
	}
	
	private Vuelo getVuelo(int IdVuelo) {
		return restClient.get()
						 .uri(urlVuelos + "vuelo/" + IdVuelo)
						 .retrieve()
						 .body(Vuelo.class);
	}
	
	@Override
	public boolean alta(ReservaDTO reserva, int plazas) {
		try {
			restClient.put()
					  .uri(urlVuelos +
							  "vueloupdate/" +
							  reserva.getVueloDTO().getIdVuelo() +
							  "/" + plazas)
					  .header("Authorization", "Bearer " + token)
					  .retrieve()
					  .toBodilessEntity();
		}
		catch (Exception ex) {
			/* Excepción por caducidad del token: lo regeneramos y reejecutamos.
			NO ES CORRECTO porque si hay una excepción por otro motivo no está contemplada,
			con lo que reejecutaríamos en bucle para nada y sin saber el error. */
			token = getToken();
			alta(reserva, plazas);			
		}
		hotel = getHotel(reserva.getHotelDTO().getIdHotel());
		vuelo = getVuelo(reserva.getVueloDTO().getIdVuelo());
		reserva.setPrecio( plazas * (vuelo.getPrecio()+hotel.getPrecio()) );
		dao.save(mapper.reservaDTOtoEntity(reserva));
		return true;
	}

	@Override
	public List<ReservaDTO> reservas(String usuario) {
		return dao.findAllByUsuario(usuario)
				  .stream()
				  .map( r -> mapper.reservaEntityToDTO(r) )
				  .toList();
	}
}