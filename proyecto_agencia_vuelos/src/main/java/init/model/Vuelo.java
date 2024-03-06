package init.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vuelos")
public class Vuelo {
	@Id
	private Integer idVuelo;
	private String company;
	private String fecha;
	private double precio;
	private int plazas;
	private String destino;
}