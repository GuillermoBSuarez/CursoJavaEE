package service.implementations;

import java.util.List;

import dtos.ClienteDTO;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Cliente;
import service.interfaces.ClienteService;
import service.mappers.Mapeador;

@Named(value="ClienteServiceImpl1")
public class ClienteServiceImpl implements ClienteService {
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("libreriaPU").createEntityManager();
	}
	
	public ClienteDTO autenticar(String usuario, String password) {
		List<Cliente> clientes = getEntityManager()
				.createQuery("select c from Cliente c where c.usuario=?1 and c.password=?2", Cliente.class)
				.setParameter(1, usuario)
				.setParameter(2, password)
				.getResultList();
		return clientes.size() > 0 ?
				Mapeador.clienteEntityToDTO(clientes.get(0)) :
				null;
		/* clientes es una lista de objetos Cliente. En código aceptaría un cast a ClienteDTO,
		pero NO CUELA, da error de ejecución. Así que al mapeador */
	}
	
	@Override
	public void altaCliente(ClienteDTO cliente) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(Mapeador.clienteDTOtoEntity(cliente)); // la persistencia trabaja con entidades, no DTOs.
		tx.commit();  
	}
}