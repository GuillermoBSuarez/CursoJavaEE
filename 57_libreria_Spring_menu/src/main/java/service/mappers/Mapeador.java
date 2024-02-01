package service.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dtos.ClienteDTO;
import dtos.LibroDTO;
import dtos.TemaDTO;
import model.Cliente;
import model.Libro;
import model.Tema;
import service.interfaces.LibroService;

@Component
public class Mapeador {
	
	@Autowired
	LibroService service;
	
	public TemaDTO temaEntityToDTO(Tema tema) {
		return new TemaDTO(tema.getIdTema(), tema.getTema());
	}
	
	public LibroDTO libroEntityToDTO(Libro libro) {
		return new LibroDTO(
				libro.getIsbn(),
				libro.getTitulo(),
				libro.getAutor(),
				libro.getPrecio(),
				libro.getPaginas(),
				service.getTema(libro.getTema().getIdTema()));
	}
	
	public ClienteDTO clienteEntityToDTO(Cliente cliente) {
		return new ClienteDTO(cliente.getUsuario(), cliente.getPassword(), cliente.getEmail(), cliente.getTelefono());
	}
	
	public Cliente clienteDTOtoEntity(ClienteDTO cliente) {
		return new Cliente(0, cliente.getUsuario(), cliente.getPassword(), cliente.getEmail(), cliente.getTelefono());
	}
}