package init.interfaces;

import java.util.List;

import init.model.Coche;

public interface CocheService {
	void alta(Coche coche);
	Coche eliminar(String matricula);
	List<Coche> cochesMarca(String marca);
	List<Coche> cochesPrecioMax(double precio);
	List<Coche> coches();
	void actualizar(Coche coche);
}