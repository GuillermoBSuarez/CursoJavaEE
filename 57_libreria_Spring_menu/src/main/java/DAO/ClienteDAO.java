package DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer> {
	Cliente findByUsuarioAndPassword(String usuario, String password);
	Cliente findByUsuario(String usuario);
}