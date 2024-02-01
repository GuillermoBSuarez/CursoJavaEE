package service.interfaces;

import java.util.List;

import dtos.LibroDTO;
import dtos.TemaDTO;

public interface LibroService {
	List<TemaDTO> getTemas();

	List<LibroDTO> getLibrosTema(int idTema);	// List<Libro> findByTema(int idTema);
	LibroDTO getLibro(int isbn);
	
	TemaDTO getTema(int idTema);
}