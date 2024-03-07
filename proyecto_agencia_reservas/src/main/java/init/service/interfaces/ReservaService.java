package init.service.interfaces;

import java.util.List;

import init.dto.ReservaDTO;
import init.model.Reserva;

public interface ReservaService {
	
	boolean alta(ReservaDTO reserva);
	
	List<ReservaDTO> reservas(String usuario);
}