package init.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {
	@Id
	private int idHotel;
	private String nombre;
	private int categoria;
	private double precio;
	private boolean disponible;
	private String localizacion;	
}
