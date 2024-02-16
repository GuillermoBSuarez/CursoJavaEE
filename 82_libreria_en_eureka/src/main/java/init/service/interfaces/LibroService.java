package init.service.interfaces;

import java.util.List;

import init.model.Libro;

public interface LibroService {
	List<Libro> libros();
	List<String> tematicas();
	Libro libro(int isbn);
	boolean alta(Libro libro);
}