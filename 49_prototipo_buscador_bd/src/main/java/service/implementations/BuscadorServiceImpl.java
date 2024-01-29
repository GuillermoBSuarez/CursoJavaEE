package service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Resultado;
import service.interfaces.BuscadorService;

@Service
public class BuscadorServiceImpl implements BuscadorService {

	@PersistenceContext		// Inyecta el EntityManager
	EntityManager em;

	@Override
	public List<Resultado> buscar(String tematica) {
		return em.createQuery("select r from Resultado r where r.tematica=?1", Resultado.class).setParameter(1, tematica)
				.getResultList();
	}

	@Override
	@Transactional			// Ojo al importar, que hay otra etiqueta Transactional de Jakarta
	public void agregar(Resultado resultado) {
		em.persist(resultado);
	}
}