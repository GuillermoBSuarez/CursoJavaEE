package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Hotel;
import java.util.List;


public interface HotelDAO extends JpaRepository<Hotel, Integer> {
	List<Hotel> findAllByLocalizacion(String localizacion);
}