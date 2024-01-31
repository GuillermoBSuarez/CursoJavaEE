package service.interfaces;

import java.util.List;

import model.Libro;
import model.Tema;

public interface LibroService {
	List<Tema> getTemas();

	List<Libro> getLibrosTema(int idTema);	// List<Libro> findByTema(int idTema);
	Libro getLibro(int isbn);
	
	Tema getTema(int idTema);
}