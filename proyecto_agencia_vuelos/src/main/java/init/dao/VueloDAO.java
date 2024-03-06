package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Vuelo;

public interface VueloDAO extends JpaRepository<Vuelo, Integer> {
	List<Vuelo> findAllByDestino(String destino);
}