package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	private String nombre;
	private double precio;
	private String categoria;
}