package service.mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dtos.ClienteDTO;
import dtos.LibroDTO;
import dtos.TemaDTO;
import dtos.VentaDTO;
import model.Cliente;
import model.Libro;
import model.Tema;
import model.Venta;
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
		return new Cliente(0,
				cliente.getUsuario(),
				cliente.getPassword(),
				cliente.getEmail(),
				cliente.getTelefono(),
				null);	/* En el bean de Cliente hemos añadido el atributo ventas, así que este constructor lo exige.
						Añadimos null porque no las necesitamos para dar de alta un cliente. */
	}
	
	public Libro libroDTOToEntity(LibroDTO libro) {
		return new Libro(libro.getIsbn(),
						 libro.getTitulo(),
						 libro.getAutor(),
						 libro.getPrecio(),
						 libro.getPaginas(),
						 null);
	}
	
	public VentaDTO ventaEntityToDTO(Venta venta) {
		return new VentaDTO( venta.getIdVenta(),
							 venta.getCliente().getUsuario(),
							 venta.getLibro().getTitulo(),
							 convertirDateALocalDate(venta.getFecha()));
	}	
	
	// Conversor de LocalDate a Date, sacado de Google.
	private LocalDate convertirDateALocalDate(Date date) {
		 return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
}