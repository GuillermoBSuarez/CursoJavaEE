package service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.LibroDAO;
import DAO.TemaDAO;
import dtos.LibroDTO;
import dtos.TemaDTO;
import model.Libro;
import service.interfaces.LibroService;
import service.mappers.Mapeador;

@Service
public class LibroServiceImpl implements LibroService {

	/* Inyectamos ambos DAO y cada uno con su etiqueta	@Autowired
	porque no vamos a hacer un service para Tema, se unifica aquí. */
	@Autowired
	LibroDAO libroDAO;
	@Autowired
	TemaDAO temaDAO;
	@Autowired
	Mapeador mapeador;

	@Override
	public List<TemaDTO> getTemas() {
		return temaDAO.findAll()
				.stream()
				.map( e -> mapeador.temaEntityToDTO(e) )
				.toList();
	}

	@Override
	public List<LibroDTO> getLibrosTema(int idTema) {
		return (idTema == 0) ?
				libroDAO.findAll()
						.stream()
						.map(a -> mapeador.libroEntityToDTO(a))
						.toList() :
				libroDAO.findByIdTema(idTema)
						.stream()
						.map(a -> mapeador.libroEntityToDTO(a))
						.toList();
	}

	@Override
	public LibroDTO getLibro(int isbn) {
		Optional<Libro> libro = libroDAO.findById(isbn);
		return libro.map( a -> mapeador.libroEntityToDTO(a))	// Devuelve un Optional de LibroDTO
					.orElse(null);
	}

	@Override
	public TemaDTO getTema(int idTema) {
		return temaDAO.findById(idTema)
					  .map( a -> mapeador.temaEntityToDTO(a) )	// Devuelve un Optional de LibroDTO
					  .orElse(null);
	}

}
