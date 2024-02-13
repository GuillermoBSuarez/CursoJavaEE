package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.exceptions.CursoExistenteException;
import init.model.Curso;
import service.interfaces.CursoService;

@RestController
public class cursosController {

	@Autowired
	CursoService service;

	@GetMapping(value = "cursos", produces = "application/json")
	public ResponseEntity<List<Curso>> cursos() {
		return new ResponseEntity<List<Curso>>(service.cursos(), HttpStatus.OK);
	}
	
	@GetMapping(value = "curso", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curso> curso(@RequestParam("idCurso") int idCurso) {
		Curso curso = service.curso(idCurso);
		if (curso != null) return new ResponseEntity<Curso>(curso, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "cursos/{precioMin}/{precioMax}", produces = "application/json")
	public List<Curso> cursosPrecios(@PathVariable("precioMin") double precioMin, @PathVariable("precioMax") double precioMax) {
		return service.cursosPrecios(precioMin, precioMax);
	}

	@PostMapping(value = "agregar", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Curso>> agregarCurso(@RequestBody Curso curso) {
		try {
			// intentamos enviar el resultado de la función y OK, si es que devuelve una lista.
			return new ResponseEntity<List<Curso>>(service.agregarCurso(curso), HttpStatus.OK);
															// cuerpo			código de estado, en este caso, 200
		}
		catch (CursoExistenteException ex) {		// Si la llamada al método lanza una excepción...
			return new ResponseEntity<>(HttpStatus.CONFLICT);		// enviamos un mensaje de conflico porque ya existe ese curso.
		}		
	}
	
	@DeleteMapping(value = "eliminarCurso", produces = "application/json")
	public Curso eliminarCurso(@RequestParam("denominacion") String denominacion){
		return service.eliminarCurso(denominacion);
	}
	
	@PutMapping(value = "actualizarPrecio")
	public void actualizarPrecio(@RequestParam("porcentaje") int porcentaje, @RequestParam("denominacion") String denomonacion) {
		service.actualizarPrecio(porcentaje, denomonacion);
	}	
}