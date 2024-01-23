package service.mappers;

import dtos.LibroDTO;
import dtos.TemaDTO;
import model.Libro;
import model.Tema;
import service.LibrosService;

public class Mapeador {
	
	public static TemaDTO temaEntityToDTO(Tema tema) {
		return new TemaDTO(tema.getIdTema(), tema.getTema());
	}
	
	public static LibroDTO libroEntityToDTO(Libro libro) {
		// Libro tiene un campo que referencia a Tema, idTema, por lo que necesitamos...
		LibrosService service = new LibrosService();
		
		return new LibroDTO(
				libro.getIsbn(),
				libro.getTitulo(),
				libro.getAutor(),
				libro.getPrecio(),
				libro.getPaginas(),
				service.getTema(libro.getIdTema()));
	}		
}