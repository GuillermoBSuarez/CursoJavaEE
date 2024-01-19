package service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Producto;

public class ProductosService {
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("productosPU");
		return factory.createEntityManager();
	}

	public void agregarProducto(String nombre, String categoria, double precio) {
		Producto pr = new Producto(0, nombre, categoria, precio);
		/* El id a 0 pero podría ser cualquier valor, ya que el motor de persistencia no lo usará
		al hacer el insert */
		
		EntityManager em = getEntityManager();
		
		// las operaciones que modifican la BD deben ir dentro de un objeto transacción.
		EntityTransaction tx = em.getTransaction();		// creamos la transacicón
		tx.begin();										// la empezamos
		em.persist(pr);									// añadimos la operación
		tx.commit();									// y la confirmamos
	}
	
	public Producto buscarProducto(int idProducto) {
		return getEntityManager().find(Producto.class, idProducto);
	}
	
	public void eliminarProducto(int idProducto) {
		// buscamos el producto y, si existe, eliminamos
		Producto pr = buscarProducto(idProducto);
		if (pr != null) {
			EntityTransaction tx = getEntityManager().getTransaction();
			tx.begin();
			getEntityManager().remove(pr);
			tx.commit();
		}
	}
	
	public List<Producto> buscarPorCategoria(String categoria){
		// consulta parametrizada por posición
		String jpql = "select p from Producto p where p.categoria = ?1";
		// se puede parametrizar por nombre:          p.categoria = :cat;
		// y después:		query.setParameter(cat, categoria);
		TypedQuery<Producto> query = getEntityManager().createQuery(jpql, Producto.class);
		query.setParameter(1, categoria);
		return query.getResultList();
	}
	
	public void eliminarPorNombre(String nombre) {
		String jpql = "delete from Producto p where p.nombre = ?1";
		EntityManager em = getEntityManager();
		Query query = em.createQuery(jpql);
		query.setParameter(1, nombre);
		
		// es necesaria una transacción
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();
	}
}