package service.interfaces;

import dtos.ClienteDTO;

public interface ClienteService {
	boolean agregar(ClienteDTO cliente);
	ClienteDTO buscar(String usuario, String password);
}