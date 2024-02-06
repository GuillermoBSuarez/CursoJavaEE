package service.implementations;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Curso;

public class CursoServiceImpl implements CursoService{
	
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("escuelaPU").createEntityManager();
	}
	
	@Override
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
	
	@Override
	public void eliminarCurso(String denominacion) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("delete from Curso c where c.nombre = ?1");
		query.setParameter(1, denominacion);
		EntityTransaction et = em.getTransaction();
		et.begin();
		query.executeUpdate();
		et.commit();
	}
	
	@Override
	public List<Curso> buscarPorPrecio(double precio){
		String jpql = "select c from Curso c where c.precio <= ?1";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, precio);
		return (List<Curso>) query.getResultList();
	}
	
	@Override
	public List<Curso> buscarPorDuracion(int duracionMin, int duracionMax){
		String jpql = "select c from Curso c where c.duracion >= ?1 and c.duracion <= ?2";
		TypedQuery<Curso> query = getEntityManager().createQuery(jpql, Curso.class);
		query.setParameter(1, duracionMin);
		query.setParameter(2, duracionMax);
		return query.getResultList();
	}
	
	@Override
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