package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Formacion {
	
	@JsonProperty(value = "denominacion")	// Para serializar y deserializar el campo tal y como lo maneja el webservice como objeto Curso
	private String nombre;
	@JsonProperty(value = "duracion")
	private int horas;
	private double precio;
}

// No definimos entity porque no es una entidad, no va a acceder a BD.