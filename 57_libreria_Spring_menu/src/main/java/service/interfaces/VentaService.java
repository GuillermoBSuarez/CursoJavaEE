package service.interfaces;

import java.util.List;

import dtos.LibroDTO;
import dtos.VentaDTO;

public interface VentaService {
	List<VentaDTO> getVentasCliente(String usuario);
	void registarCompra(String usuario, List<LibroDTO> libros);
}