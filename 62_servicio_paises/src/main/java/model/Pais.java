package model;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pais {
	
	@JsonAlias(value = "name")		private String nombre;
	@JsonAlias(value = "region")	private String continente;
	/* Se llama igual en el json*/	private String capital;
	@JsonAlias(value = "population")private long poblacion;
	@JsonAlias(value = "flag")		private String bandera;
}