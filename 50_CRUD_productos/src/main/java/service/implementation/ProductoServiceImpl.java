package service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import model.Producto;
import service.interfaces.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	@PersistenceContext
	EntityManager em;
	
	// Método común a agregar y eliminar
	private Producto buscarProducto(String nombre) {
		List<Producto> productos = em.createQuery("select p from Producto p where p.nombre = ?1", Producto.class)
									 .setParameter(1, nombre)
									 .getResultList();
		return productos.size() > 0 ? productos.get(0) : null ;
	}
	
	@Override
	@Transactional
	public boolean agregar(Producto producto) {				// C-reate
		if ( buscarProducto(producto.getNombre()) != null ) // Si encuentra un producto con ese nombre...
			return false;
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(producto);
		tx.commit();
		return true;
	}

	@Override
	public List<Producto> buscar(String categoria) {		// R-ead
		return em.createQuery("select p from Producto p where p.categoria = ?1", Producto.class)
				 .setParameter(1, categoria)
				 .getResultList();
	}

	@Override
	@Transactional
	public void actualizar(String nombre, Double precio) {	// U-pdate
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createQuery("update Producto p set p.precio = ?2 where p.nombre = ?1", Producto.class)
		  .setParameter(1, nombre)
		  .setParameter(2, precio)
		  .executeUpdate();
		tx.commit();
	}
	
	@Override
	@Transactional
	public Producto eliminar(String nombre) {				// D-elete
		Producto producto = buscarProducto(nombre);
		if (producto == null) return null;					// Si no encuentra el producto a borrar, null
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createQuery("delete from Producto p where p.nombre = '" + nombre + "'")
		  .executeUpdate();
		tx.commit();
		return producto;
	}	
}