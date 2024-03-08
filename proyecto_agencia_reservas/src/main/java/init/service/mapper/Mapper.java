package init.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import init.dto.HotelDTO;
import init.dto.ReservaDTO;
import init.dto.VueloDTO;
import init.model.Hotel;
import init.model.Reserva;
import init.model.Vuelo;
import init.service.interfaces.ReservaService;

@Component
public class Mapper {

	public HotelDTO hotelEntityToDTO(Hotel hotel) {
		return new HotelDTO(hotel.getIdHotel(),
							hotel.getNombre(),
							hotel.getCategoria(),
							hotel.getPrecio(),
							hotel.isDisponible(),
							hotel.getLocalizacion());
	}
	
	public VueloDTO vueloEntityToDTO(Vuelo vuelo) {
		return new VueloDTO(vuelo.getIdVuelo(),
							vuelo.getCompany(),
							vuelo.getFecha(),
							vuelo.getPrecio(),
							vuelo.getPlazas(),
							vuelo.getDestino());
	}
	
	public ReservaDTO reservaEntityToDTO(Reserva reserva) {
		return new ReservaDTO(reserva.getIdReserva(),
							  reserva.getUsuario(),
							  hotelEntityToDTO(reserva.getHotel()),
							  vueloEntityToDTO(reserva.getVuelo()),
							  reserva.getPrecio());
	}
	
	public Hotel hotelDTOtoEntity(HotelDTO hotel) {
		return new Hotel(hotel.getIdHotel(),
						 hotel.getNombre(),
						 hotel.getCategoria(),
						 hotel.getPrecio(),
						 hotel.isDisponible(),
						 hotel.getLocalizacion());
	}
	
	public Vuelo vueloDTOtoEntity(VueloDTO vuelo) {
		return new Vuelo(vuelo.getIdVuelo(),
						 vuelo.getCompany(),
						 vuelo.getFecha(),
						 vuelo.getPrecio(),
						 vuelo.getPlazas(),
						 vuelo.getDestino());
	}
	
	public Reserva reservaDTOtoEntity(ReservaDTO reserva) {
		return new Reserva(reserva.getIdReserva(),
						   reserva.getUsuario(),
						   hotelDTOtoEntity(reserva.getHotelDTO()),
						   vueloDTOtoEntity(reserva.getVueloDTO()),
						   reserva.getPrecio());
	}
}