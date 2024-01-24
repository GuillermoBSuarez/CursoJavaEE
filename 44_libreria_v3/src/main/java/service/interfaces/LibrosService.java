package service.interfaces;

import java.util.List;

import dtos.LibroDTO;
import dtos.TemaDTO;

public interface LibrosService {

	List<TemaDTO> getTemas();

	List<LibroDTO> getLibrosTema(int idTema);

	LibroDTO getLibro(int isbn);

	TemaDTO getTema(int idTema);
}