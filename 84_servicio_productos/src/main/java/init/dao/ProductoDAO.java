package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Producto;

public interface ProductoDAO extends JpaRepository<Producto, Integer> {}