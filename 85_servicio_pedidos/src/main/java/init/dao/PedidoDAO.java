package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Integer> {}