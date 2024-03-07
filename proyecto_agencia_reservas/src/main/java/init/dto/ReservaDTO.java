package init.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ReservaDTO {
	@Id
	private int idReserva;
	private String usuario;
	@JsonProperty(value = "hotel")
	private HotelDTO hotelDTO;
	@JsonProperty(value = "vuelo")
	private VueloDTO vueloDTO;
	private double precio;
	
	public ReservaDTO() {
		vueloDTO = new VueloDTO();
		hotelDTO = new HotelDTO();
	}
} 