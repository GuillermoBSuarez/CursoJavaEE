package service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Libro;
import model.Tema;

public class LibrosService {
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("libreriaPU");
		return factory.createEntityManager();
	}
	
	public List<Tema> getTemas(){
		return getEntityManager()
				.createQuery("select t from Tema t", Tema.class)
				.getResultList();
	}
	
	public List<Libro> getLibrosTema(int idTema){
		String query = (idTema == 0) ?
				"select l from Libro l" :
				"select l from Libro l where l.idTema=" + idTema ;
		return getEntityManager()
				.createQuery(query, Libro.class)
				.getResultList();
	}
}