package init.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import init.dto.ReservaDTO;
import init.model.Reserva;

public interface ReservaDAO extends JpaRepository<Reserva, Integer>  {
	List<Reserva> findAllByUsuario(String usuario);
}
