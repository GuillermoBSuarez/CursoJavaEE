package DAO.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import DAO.interfaces.ProductosDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Producto;

@Repository			// Similar a @Service pero más adecuado para DAO
public class ProductoDAOImpl implements ProductosDAO {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public void save(Producto producto) {
		em.persist(producto);
	}

	@Override
	public Producto findByNombre(String nombre) {
		List<Producto> productos = em.createQuery("select p from Producto p where p.nombre = ?1", Producto.class)
				 .setParameter(1, nombre)
				 .getResultList();
		return productos.size() > 0 ? productos.get(0) : null ;
	}

	@Override
	public List<Producto> findByCategoria(String categoria) {
		return em.createQuery("select p from Producto p where p.categoria = ?1", Producto.class)
				 .setParameter(1, categoria)
				 .getResultList();
	}

	@Override
	@Transactional
	public void update(Producto producto) {
		em.merge(producto);
	}

	@Override
	@Transactional
	public void delete(Producto producto) {
		/* em.remove(producto);
		No sirve porque producto está "fuera del contexto de persistencia", detached,
		porque después de buscarlo antes en el find, la ejecución ha salido del contexto 
		de persistencia, es decir, del EntityManager. Al volver para hacer delete
		se crea OTRO EntityManager y ya no entiende el producto como parte del contexto
		de persistencia, hay que buscarlo nuevamente, o bien borrar por nombre.
		
		Más sencillo: em.remove(em.merge(producto));
		porque merge sí devuelve una entidad */
		em.remove(em.find(Producto.class, producto.getIdProducto()));
	}

	@Override
	@Transactional
	public void deleteByNombre(String nombre) {
		em.createQuery("delete from Producto p where p.nombre = '" + nombre + "'")
		  .executeUpdate();
	}
}