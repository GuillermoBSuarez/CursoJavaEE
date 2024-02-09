package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Resultado;
import service.interfaces.BuscadorService;

@CrossOrigin("*")		// Permitimos llamadas desde cualquier origen.
@RestController	// Dice a Spring que todos los métodos devuelven los datos en
				// la respuesta, no hace¡ falta Model porque no hay vistas.
public class buscadorController {
	@Autowired
	BuscadorService buscadorService;

	/* Desaparecen los return de strings, eran para dirigir a páginas web,
	pero ya no hay, son servicios rest que devuelven datos, no páginas */

	@GetMapping(value = "buscar", produces = "application/json")
	/* produces indica el tipo de dato del cuerpo de la respuesta.
	En este caso es optativo porque por defecto es json. */
	public List<Resultado> buscar(@RequestParam("tematica") String tematica) {
		return buscadorService.buscar(tematica);
	}
	
	@PostMapping(value = "agregar", consumes = "application/json")
	/* consumes indica el tipo de dato que viene en el cuerpo de la llamada.
	En este caso también es optativo por el mismo motivo. */
	public void agregar(@RequestBody Resultado resultado) {
		// Cambia @ModelAttribute a @RequestBody para volver el cuerpo de la petición en el javabean
		buscadorService.agregar(resultado);
	}
	
	@DeleteMapping(value = "borrar", produces = "application/json")
	public List<Resultado> eliminar(@RequestParam("url") String url){
		return buscadorService.eliminarResultado(url);
	}
	
	@PutMapping(value = "actualizar", produces = "application/json", consumes = "application/json")
	public Resultado actualizar(@RequestBody Resultado resultado) {
		/* Podríamos hacerlo con dos parámetros -url y descripción- pero lo hacemos con un objeto Resultado
		por variar y ver distintos métodos. */
		return buscadorService.actualizarDescripcion(resultado.getUrl(), resultado.getDescripcion());
	}
	
}