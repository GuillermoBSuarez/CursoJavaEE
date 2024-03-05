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
@Table(name = "hoteles")
public class Hotel {
	@Id
	private Integer idHotel;
	private String nombre;
	private Integer categoria;
	private double precio;
	private boolean disponible;
	private String localizacion;
}