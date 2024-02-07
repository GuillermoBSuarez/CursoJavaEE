package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Formacion {
	private String nombre;
	private int horas;
	private double precio;
}

// No definimos entity porque no es una entidad, no va a acceder a BD.