package init.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.dao.ProductoDAO;
import init.model.Producto;
import init.service.interfaces.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	ProductoDAO dao;
	
	@Override
	public List<Producto> productos() {
		return dao.findAll();
	}

	@Override
	/* @Query("update Producto p set p.stock = p.stock-?2 where p.codigoProducto = ?1") */
	public void actualizarStock(int codigoProducto, int unidades) {
		Producto producto = dao.findById(codigoProducto).get();
		producto.setStock(producto.getStock() - unidades);
		dao.save(producto);
	}

	@Override
	public double precioProducto(int codigoProducto) {
		return dao.findById(codigoProducto).get().getPrecioUnitario();
	}
}