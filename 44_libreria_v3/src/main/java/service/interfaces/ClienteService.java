package service.interfaces;

import dtos.ClienteDTO;

public interface ClienteService {
	ClienteDTO autenticar(String usuario, String password);
	void altaCliente(ClienteDTO cliente);
}