package init.service.interfaces;

import init.model.Hotel;

public interface ClienteService {
	
	Hotel clienteUP(String usuario, String password);		// por Usuario y Password
	
	Hotel clienteU(String usuario);						// por Usuario
	
	Hotel alta(Hotel cliente);
}
