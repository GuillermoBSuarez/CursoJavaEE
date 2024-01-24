package service.implementations;

import java.util.List;

import dtos.LibroDTO;
import dtos.TemaDTO;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Libro;
import model.Tema;
import service.interfaces.LibrosService;
import service.mappers.Mapeador;

@Named(value="impl1")
public class LibrosServiceImpl implements LibrosService {
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("libreriaPU");
		return factory.createEntityManager();
	}
	
	@Override
	public List<TemaDTO> getTemas(){
		return getEntityManager()
				.createQuery("select t from Tema t", Tema.class)
				.getResultList()
				.stream()
				.map( t -> Mapeador.temaEntityToDTO(t))
				.toList();
	}
	
	@Override
	public List<LibroDTO> getLibrosTema(int idTema){
		return getEntityManager()
				.createQuery( (idTema == 0) ?
						"select l from Libro l" :
						"select l from Libro l where l.idTema=" + idTema , Libro.class )
				.getResultList()
				.stream()				// Ojo, que se te olvidaba, que hasta ahora no hay stream
				.map( l -> Mapeador.libroEntityToDTO(l) )
				.toList();
	}
	
	@Override
	public LibroDTO getLibro(int isbn) {
		return Mapeador.libroEntityToDTO(getEntityManager().find(Libro.class, isbn));
	}
	
	@Override
	public TemaDTO getTema(int idTema) {
		return Mapeador.temaEntityToDTO(getEntityManager().find(Tema.class, idTema));
	}
}