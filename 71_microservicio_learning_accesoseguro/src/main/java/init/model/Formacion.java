package init.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Formacion {
	
	@JsonProperty(value = "denominacion")
	private String nombre;
	@JsonProperty(value = "duracion")
	private int horas;
	private double precio;
}