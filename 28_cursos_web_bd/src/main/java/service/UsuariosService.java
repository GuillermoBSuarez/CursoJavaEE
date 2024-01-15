package service;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Curso;
import model.Usuario;

public class UsuariosService {
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("escuelaPU").createEntityManager();
	}
	
	public boolean autenticar(String user, String pass){
		String jpql = "select u from Usuario u where u.usuario=?1 and u.password=?2";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter(1, user);
		query.setParameter(2, pass);
		return query.getResultList().size() > 0;
	}
}