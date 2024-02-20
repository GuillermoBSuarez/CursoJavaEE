package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Pais;
import init.service.interfaces.PaisesService;

@Service
public class PaisesServiceImpl implements PaisesService {

	@Autowired
	RestClient rc;

	String urlBase = "https://restcountries.com/v2/all";
	
	private List<Pais> paises() {
		return Arrays.asList(rc.get()
							   .uri(urlBase)
							   .retrieve()
							   .body(Pais[].class));
	}
	
	@Override
	public List<String> continentes() {
		return paises().stream()
					   .map( p -> p.getContinente() )
					   .distinct()
					   .toList();
	}

	@Override
	public List<Pais> paisesContinente(String continente) {
		return paises().stream()
					   .filter( p -> p.getContinente().equals(continente) )
					   .toList();
	}

	@Override
	public Pais paisMasPoblado() {
		return paises().stream()
					   .max( (p1,p2) -> (int)(p1.getPoblacion() - p2.getPoblacion()) )
					   .get();
	}
}
