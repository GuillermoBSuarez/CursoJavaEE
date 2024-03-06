package init.service.interfaces;

import java.util.List;

import init.model.Hotel;

public interface HotelService {

	Hotel hotel(int idHotel);

	List<Hotel> hoteles(String localizacion);
	
	List<String> localizaciones();
}