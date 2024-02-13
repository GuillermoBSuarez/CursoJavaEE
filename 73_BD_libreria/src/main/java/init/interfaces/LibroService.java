package init.interfaces;

import java.util.List;

import init.model.Libro;

public interface LibroService {
	List<Libro> libros();
	List<String> tematicas();
	Libro libro(int isbn);
	void alta(Libro libro);
}