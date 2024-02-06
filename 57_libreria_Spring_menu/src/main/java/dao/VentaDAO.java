package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Tema;
import model.Venta;

public interface VentaDAO extends JpaRepository<Venta, Integer> {

	/* No son necesarias las etiquetas @Query porque las condiciones afectan a la entidad 1 de 1-muchos,
	aunque se podrían poner. */
	List<Venta> findByClienteIdCliente(int idCliente);	// Ventas por usuario de cliente
	List<Venta> findByLibroIsbn(int isbn);				// Ventas por titulo de libro
	List<Venta> findByFecha(Date fecha);				// Ventas por fecha
	
	/* Sólo de ejemplo, para ver cómo buscar por rango de fechas. Con poner Between basta.
	Eso sí, debe ser Date de java.util */
	List<Venta> findByFechaBetween(Date fecha1, Date fecha2);
}