package init.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehiculo {
	public String matrícula;
	public String tipo;
	public String marca;
	public String model;
	public int kms;
	public double precio;

	@JsonFormat(pattern="yyyy-MM-dd")
	public Date fechaFabricación;
}