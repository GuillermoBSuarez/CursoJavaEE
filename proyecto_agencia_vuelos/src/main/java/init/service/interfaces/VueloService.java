package init.service.interfaces;

import java.util.List;

import init.model.Vuelo;

public interface VueloService {

	Vuelo vuelo(int idVuelo);

	List<Vuelo> vuelos(String destino, int plazas);

	Vuelo vueloUpdate(int idVuelo, int plazas);
}