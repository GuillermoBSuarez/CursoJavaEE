package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.VueloDAO;
import init.model.Vuelo;
import init.service.interfaces.VueloService;

@Service
public class VueloServiceImpl implements VueloService {
	
	@Autowired
	VueloDAO dao;

	@Override
	public Vuelo vuelo(int idVuelo) {
		return dao.findById(idVuelo).orElse(null);
	}

	@Override
	public List<Vuelo> vuelos(String destino, int plazas) {
		return dao.findAllByDestino(destino)
				  .stream()
				  .filter( v -> v.getPlazas() >= plazas )
				  .toList();
	}

	@Override
	public Vuelo vueloUpdate(int idVuelo, int plazas) {
		Vuelo vuelo = vuelo(idVuelo);
		vuelo.setPlazas(vuelo.getPlazas() - plazas);
		return dao.save(vuelo);
	}
}
