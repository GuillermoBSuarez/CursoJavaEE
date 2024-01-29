package service.interfaces;

import java.util.List;

import model.Producto;

public interface ProductoService {
	void agregar(Producto producto);
	List<Producto> buscar(String categoria);
	void eliminar(String nombre);
}