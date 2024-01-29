package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Producto;
import service.interfaces.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	static List<Producto> productos = new ArrayList<>();

	@Override
	public void agregar(Producto producto) {
		productos.add(producto);
	}

	@Override
	public List<Producto> buscar(String categoria) {
		return productos.stream().filter(p -> p.getCategoria().equals(categoria)).toList();
	}

	@Override
	public void eliminar(String nombre) {
		productos.removeIf(p -> p.getNombre().equals(nombre));
	}
}