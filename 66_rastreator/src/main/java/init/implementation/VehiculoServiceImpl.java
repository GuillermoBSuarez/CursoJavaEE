package init.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.interfaces.VehiculoService;
import init.model.Vehiculo;

@Service
public class VehiculoServiceImpl implements VehiculoService {
	
	@Autowired
	RestClient restClient;
	
	String urlBase = "http://localhost:8000/";
	
	@Override
	public List<Vehiculo> cochesRangoKms(int kmsMin, int kmsMax) {
		return Arrays.asList(restClient.get()
									   .uri(urlBase + "/coches")
									   .retrieve()
									   .body(Vehiculo[].class))
					 .stream()
					 .peek( v -> v.setTipo("coche") )
					 .filter( v -> (v.getKms() >= kmsMin && v.getKms() <= kmsMax) )
					 .toList();
	}

	@Override
	public List<Vehiculo> cochesPrecioMax(double precioMax) {
		return Arrays.asList(restClient.get()
									   .uri(urlBase + "/cochespreciomax?precio=" + precioMax)
									   .retrieve()
									   .body(Vehiculo[].class))
					 .stream()
					 .peek( v -> v.setTipo("coche") )
					 .toList();
	}
}