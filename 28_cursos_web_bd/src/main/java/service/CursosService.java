package service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Curso;

public class CursosService{
	
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("escuelaPU").createEntityManager();
	}

	public boolean agregarCurso (String denominacion, int duracion, double precio) {
		if (buscarCurso(denominacion).size() == 0) {
			Curso c = new Curso (0, denominacion, duracion, precio);
			
			EntityManager em = getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(c);
			tx.commit();
			return true;
		}
		return false;
	} 
	
	private List<Curso> buscarCurso(String denominacion) {
		String jpql = "select c from Curso c where c.nombre = ?1";
		TypedQuery<Curso> query = getEntityManager().createQuery(jpql, Curso.class);
		query.setParameter(1, denominacion);
		return query.getResultList();
	}
	
	public void eliminarCurso(String denominacion) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("delete from Curso c where c.nombre = ?1");
		query.setParameter(1, denominacion);
		EntityTransaction et = em.getTransaction();
		et.begin();
		query.executeUpdate();
		et.commit();
	}
	
	public List<Curso> buscarPrecio(double precio){
		String jpql = "select c from Curso c where c.precio <= ?1";
		TypedQuery<Curso> query = getEntityManager().createQuery(jpql, Curso.class);
		query.setParameter(1, precio);
		return query.getResultList();
	}
	
	public void modificarDuracion(String denominacion, int nuevaDuracion) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("update Curso c set c.duracion = ?1 where c.nombre = ?2");
		query.setParameter(1, nuevaDuracion);
		query.setParameter(2, denominacion);
		EntityTransaction et = em.getTransaction();
		et.begin();
		query.executeUpdate();
		et.commit();
	}
}