package service.interfaces;

import java.util.List;

import model.Resultado;

public interface BuscadorService {
	List<Resultado> buscar(String tematica);

	void agregar(Resultado resultado);
	
	/* Añadimos método para eliminar, que partiendo de la url
	elimine el resultado y devuelva el resto de resultados. */
	List<Resultado> eliminarResultado(String url);
	
	// Añadimos método para actualizar (PUT) la descripción de un resultado
	Resultado actualizarDescripcion(String url, String nuevaDescripcion);
}