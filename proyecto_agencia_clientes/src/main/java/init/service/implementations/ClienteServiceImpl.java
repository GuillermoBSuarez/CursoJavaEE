package init.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.ClienteDAO;
import init.model.Cliente;
import init.service.interfaces.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteDAO dao;

	@Override
	public Cliente clienteUP(String usuario, String password) {
		return dao.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public Cliente clienteU(String usuario) {
		return dao.findById(usuario).orElse(null);
	}
	
	@Override
	public Cliente alta(Cliente cliente) {
		return (clienteU(cliente.getUsuario()) == null)?
				dao.save(cliente) :
				null;
	}
}
