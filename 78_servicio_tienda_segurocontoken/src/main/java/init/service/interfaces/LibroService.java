package init.service.interfaces;

import java.util.List;

import init.model.Libro;

public interface LibroService {
	List<String> tematicas();
	List<Libro> libros();
}