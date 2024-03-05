package init.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.HotelDAO;
import init.model.Hotel;
import init.service.interfaces.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	HotelDAO dao;

	@Override
	public Hotel clienteUP(String usuario, String password) {
		return dao.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public Hotel clienteU(String usuario) {
		return dao.findById(usuario).orElse(null);
	}
	
	@Override
	public Hotel alta(Hotel cliente) {
		return (clienteU(cliente.getUsuario()) == null)?
				dao.save(cliente) :
				null;
	}
}
