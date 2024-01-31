package service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.LibroDAO;
import DAO.TemaDAO;
import model.Libro;
import model.Tema;
import service.interfaces.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	/* Inyectamos ambos DAO y cada uno con su etiqueta	@Autowired
	porque no vamos a hacer un service para Tema, se unifica aquí. */
	LibroDAO libroDAO;
	@Autowired
	TemaDAO temaDAO;

	@Override
	public List<Tema> getTemas() {
		return temaDAO.findAll();
	}

	@Override
	public List<Libro> getLibrosTema(int idTema) {
		return (idTema == 0) ?
				libroDAO.findAll() :
				libroDAO.findByTema(idTema) ;
	}

	@Override
	public Libro getLibro(int isbn) {
		Optional<Libro> libro = libroDAO.findById(isbn);
		return libro.orElse(null);		// Similar a un operador ternario.
	}

	@Override
	public Tema getTema(int idTema) {
		// Como el anterior pero sin variable, o sea, mejor:
		return temaDAO.findById(idTema).orElse(null);
	}

}
