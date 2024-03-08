package init.service.interfaces;

import java.util.List;

import init.dto.ReservaDTO;

public interface ReservaService {
	
	boolean alta(ReservaDTO reserva, int plazas);
	
	List<ReservaDTO> reservas(String usuario);
}