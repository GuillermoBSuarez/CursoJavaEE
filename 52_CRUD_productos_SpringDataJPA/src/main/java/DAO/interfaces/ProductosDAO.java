package DAO.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import model.Producto;

public interface ProductosDAO extends JpaRepository<Producto, Integer> {
	// Eliminamos métodos que ya vienen heredados de JpaRepository.
	Producto findByNombre(String nombre);
	List<Producto> findByCategoria(String categoria);
	
	@Transactional
	@Modifying
	void deleteByNombre(String nombre);
}