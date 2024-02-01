package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "libros")
public class Libro {
	@Id
	private int isbn;
	private String titulo, autor;
	private double precio;
	private int paginas, idTema;
}	