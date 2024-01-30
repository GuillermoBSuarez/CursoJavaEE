package service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.interfaces.ProductosDAO;
import model.Producto;
import service.interfaces.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	ProductosDAO DAO;

	@Override
	// Desaparecen los @Transactional porque esta capa ya no "transacciona"
	public boolean agregar(Producto producto) {
		if (DAO.findByNombre(producto.getNombre()) != null)
			return false;

		DAO.save(producto);
		return true;
	}

	@Override
	public List<Producto> buscar(String categoria) {
		return DAO.findByCategoria(categoria);
	}

	@Override
	public void actualizar(String nombre, Double precio) {
		Producto producto = DAO.findByNombre(nombre);
		if (producto != null) {
			producto.setPrecio(precio);
			DAO.update(producto);
		}
	}

	@Override
	public Producto eliminar(String nombre) {
		Producto producto = DAO.findByNombre(nombre);
		if (producto == null)
			return null;

		DAO.delete(producto);
		// O también DAO.deleteByNombre(nombre);
		return producto;
	}
}