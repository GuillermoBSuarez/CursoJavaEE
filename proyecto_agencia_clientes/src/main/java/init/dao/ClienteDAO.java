package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, String> {
	Cliente findByUsuarioAndPassword(String usuario, String password);
}