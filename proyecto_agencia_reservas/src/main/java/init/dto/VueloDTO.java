package init.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VueloDTO {
	@Id
	private Integer idVuelo;
	private String company;
	private String fecha;
	private double precio;
	private int plazas;
	private String destino;
}
