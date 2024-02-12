package init.interfaces;

import java.util.List;

import init.model.Vehiculo;

public interface VehiculoService {
	List<Vehiculo> cochesRangoKms(int kmsMin, int kmsMax);
	List<Vehiculo> cochesPrecioMax(double precioMax);
}