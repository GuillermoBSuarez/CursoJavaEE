package service.interfaces;

import model.Cliente;

public interface ClienteService {
	boolean agregar(Cliente cliente);
	Cliente buscar(String usuario, String password);
}