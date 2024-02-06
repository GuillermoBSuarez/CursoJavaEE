package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Libro;

public interface LibroDAO extends JpaRepository<Libro, Integer> {
	// Lista de libros por nombre de tema
	@Query("select l from Libro l where l.tema.tema=?1")
	List<Libro> findByIdTema(String tema);
	
	// Lista de libros por id de Tema.
	// Hay que rehacerlo con query porque hemos eliminado el campo idTema de Libro,
	// en su lugar hay un campo Tema.
	@Query("select l from Libro l where l.tema.idTema=?1")
	List<Libro> findByIdTema(int idTema);

	List<Libro> findByAutor(String autor);
	Libro findByTitulo(String titulo);
}