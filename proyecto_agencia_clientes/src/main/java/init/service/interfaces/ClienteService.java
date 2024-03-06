package init.service.interfaces;

import init.model.Cliente;

public interface ClienteService {
	Cliente clienteUP(String usuario, String password); // por Usuario y Password

	Cliente clienteU(String usuario); // por Usuario

	Cliente alta(Cliente cliente);
}
