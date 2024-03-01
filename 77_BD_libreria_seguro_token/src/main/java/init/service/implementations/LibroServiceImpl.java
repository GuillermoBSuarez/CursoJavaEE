package init.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.LibroDAO;
import init.model.Libro;
import init.service.interfaces.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	LibroDAO dao;
	
	@Override
	public List<Libro> libros() {
		return dao.findAll();
	}
	
	@Override
	public List<String> tematicas() {
		return dao.findTematicas();
	}
	
	@Override
	public Libro libro(int isbn) {
		return dao.findById(isbn).orElse(null);
	}

	@Override
	public boolean alta(Libro libro) {
		if(libro(libro.getIsbn()) == null) {
			dao.save(libro);
			return true;
		}
		return false;
	}
}