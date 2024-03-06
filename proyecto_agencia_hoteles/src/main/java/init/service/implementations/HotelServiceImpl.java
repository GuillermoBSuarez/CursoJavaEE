package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.HotelDAO;
import init.model.Hotel;
import init.service.interfaces.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelDAO dao;

	@Override
	public Hotel hotel(int idHotel) {
		return dao.findById(idHotel).orElse(null);
	}

	@Override
	public List<Hotel> hoteles(String localizacion) {
		return dao.findAllByLocalizacion(localizacion);
	}

	@Override
	public List<String> localizaciones() {
		return dao.findAll()
				  .stream()
				  .map( l -> l.getLocalizacion() )
				  .distinct()
				  .toList();
	}
}