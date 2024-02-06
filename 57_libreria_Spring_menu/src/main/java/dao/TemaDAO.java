package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Tema;

public interface TemaDAO extends JpaRepository<Tema, Integer> {
	// Tema asociado a un determinado libro
	@Query("select t from Tema t join t.libros l where l.titulo=?1")
	Tema findByTitulo(String titulo);
}