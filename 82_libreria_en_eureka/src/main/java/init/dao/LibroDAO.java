package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Libro;

public interface LibroDAO extends JpaRepository<Libro, Integer> {
	
	/* Alternativa para pedir las temáticas sin necesidad de cargar toda la BD (findAll).
	Hacemos una jpql que sólo nos devuelva las temáticas. */
	@Query("select distinct l.tematica from Libro l")
	public List<String> findTematicas();
	/* Ver implementación en LibroServiceImpl */	
}