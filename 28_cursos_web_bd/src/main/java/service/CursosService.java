package service;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Curso;

public class CursosService{
	
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("escuelaPU").createEntityManager();
	}

	public void agregarCurso (String denominacion, int duracion, double precio) {
		Curso c = new Curso (0, denominacion, duracion, precio);
		
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(c);
		et.commit();
	}
	
	public Curso buscarCurso(String denominacion) {
		return getEntityManager().find(Curso.class, denominacion);
	}
	
	public void eliminarCurso(String denominacion) {
		if (buscarCurso(denominacion) != null) {
			EntityTransaction et = getEntityManager().getTransaction();
			et.begin();
			getEntityManager().remove(buscarCurso(denominacion));
			et.commit();
		}
	}
	
	public List<Curso> buscarPrecio(double precio){
		String jpql = "select c from Curso c where c.precio <= ?1";
		TypedQuery<Curso> query = getEntityManager().createQuery(jpql, Curso.class);
		query.setParameter(1, precio);
		return query.getResultList();
	}
	
	public void modificarDuracion(int duracion, int nuevaDuracion) {
		String jpql = "update Curso c set c.duracion = ?1 where c.duracion = ?2";
		TypedQuery<Curso> query = getEntityManager().createQuery(jpql, Curso.class);
		query.setParameter(1, duracion);
		query.setParameter(2, nuevaDuracion);
		EntityTransaction et = getEntityManager().getTransaction();
		et.begin();
		query.executeUpdate();
		et.commit();
	}
}