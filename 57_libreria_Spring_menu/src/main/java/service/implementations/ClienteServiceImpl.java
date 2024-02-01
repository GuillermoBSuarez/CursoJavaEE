package service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.ClienteDAO;
import dtos.ClienteDTO;
import model.Cliente;
import service.interfaces.ClienteService;
import service.mappers.Mapeador;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteDAO DAO;
	@Autowired
	Mapeador mapeador;
	
	@Override
	public boolean agregar(ClienteDTO cliente) {
		if ( DAO.findByUsuario(cliente.getUsuario()) != null ) return false;
		DAO.save(mapeador.clienteDTOtoEntity(cliente));
		return true;
	}

	@Override
	public ClienteDTO buscar(String usuario, String password) {
		Cliente cliente = DAO.findByUsuarioAndPassword(usuario, password);
		return cliente != null ?
				mapeador.clienteEntityToDTO(cliente) :	// Comprobación para evitar enviar null al mapeador, que peta.
				null;
	}
}