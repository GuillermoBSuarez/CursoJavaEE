package dtos;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LibroDTO {
	private int isbn;
	private String titulo, autor;
	private double precio;
	private int paginas;
	private TemaDTO temaDTO;
}