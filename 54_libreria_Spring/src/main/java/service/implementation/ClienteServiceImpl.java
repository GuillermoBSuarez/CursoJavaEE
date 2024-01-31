package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.ClienteDAO;
import model.Cliente;
import service.interfaces.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteDAO DAO;
	
	@Override
	public boolean agregar(Cliente cliente) {
		if ( DAO.findByUsuario(cliente.getUsuario()) != null ) return false;
		DAO.save(cliente);
		return true;
	}

	@Override
	public Cliente buscar(String usuario, String password) {
		return DAO.findByUsuarioAndPassword(usuario, password);
	}
}