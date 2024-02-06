package dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VentaDTO {
	/* Definimos el DTO como nos interese para usarlo luego, no tiene por qué equivaler al bean. */
	private int idVenta;
	private String usuario, titulo;
	private LocalDate fecha;		// formato de fecha de java8, por complicarlo. Ver el mapeador, cómo se convierte
}