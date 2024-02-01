package DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Libro;

public interface LibroDAO extends JpaRepository<Libro, Integer> {
	List<Libro> findByIdTema(int idTema);
	
	// ?
	Libro findByTitulo(String titulo);
	List<Libro> findByAutor(String autor);
}