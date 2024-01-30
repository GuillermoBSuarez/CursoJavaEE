package service.interfaces;

import java.util.List;

import model.Producto;

public interface ProductoService {
	boolean agregar(Producto producto);					// C-reate
	List<Producto> buscar(String categoria);			// R-ead
	void actualizar(String nombre, Double precio);		// U-pdate
	Producto eliminar(String nombre);					// D-elete
	
	// Uno más aunque no lo necesitemos, porsiaca
	List<Producto> todos();
}