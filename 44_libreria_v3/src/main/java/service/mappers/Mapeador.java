package service.mappers;

import dtos.ClienteDTO;
import dtos.LibroDTO;
import dtos.TemaDTO;
import model.Cliente;
import model.Libro;
import model.Tema;
import service.implementations.LibrosServiceImpl;
import service.interfaces.LibrosService;

public class Mapeador {
	
	public static TemaDTO temaEntityToDTO(Tema tema) {
		return new TemaDTO(tema.getIdTema(), tema.getTema());
	}
	
	public static LibroDTO libroEntityToDTO(Libro libro) {
		/* Aquí no se puede hacer la inyección de dependencias
		porque esto	no es un componente Java EE, como los Servlet.
		Los CDI sólo se aplican sobre los componentes Java EE. */
		LibrosService service = new LibrosServiceImpl();
		
		return new LibroDTO(
				libro.getIsbn(),
				libro.getTitulo(),
				libro.getAutor(),
				libro.getPrecio(),
				libro.getPaginas(),
				service.getTema(libro.getIdTema()));
	}
	
	public static ClienteDTO clienteEntityToDTO(Cliente cliente) {
		return new ClienteDTO(cliente.getUsuario(), cliente.getPassword(), cliente.getEmail(), cliente.getTelefono());
	}
	
	public static Cliente clienteDTOtoEntity(ClienteDTO cliente) {
		return new Cliente(0, cliente.getUsuario(), cliente.getPassword(), cliente.getEmail(), cliente.getTelefono());
	}
}