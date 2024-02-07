package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Curso;
import service.interfaces.CursoService;

@RestController
public class cursosController {

	@Autowired
	CursoService service;

	@GetMapping(value = "cursos", produces = "application/json")
	public List<Curso> cursos() {
		return service.cursos();
	}
	
	@GetMapping(value = "curso", produces = MediaType.APPLICATION_JSON_VALUE)
											/* Altertativa a "application/json", es lo mismo. Eso sí, ojo
											hay que importarlo de springframework */
	public Curso curso(@RequestParam("idCurso") int idCurso) {
		return service.curso(idCurso);
	}
	
	/* MÉTODOS PARA CURSOS POR RANGO DE PRECIOS
	Primera versión, con una url específica: 
	
	@GetMapping(value = "cursosPrecios", produces = "application/json")
	public List<Curso> cursosPrecios(@RequestParam("precioMin") double precioMin, @RequestParam("precioMax") double precioMax) {
		return service.cursosPrecios(precioMin, precioMax);
	} 
	
	Segunda versión, reusando la url "cursos" más variables en la misma: */
	@GetMapping(value = "cursos/{precioMin}/{precioMax}", produces = "application/json")
	public List<Curso> cursosPrecios(@PathVariable("precioMin") double precioMin, @PathVariable("precioMax") double precioMax) {
		return service.cursosPrecios(precioMin, precioMax);
	}

	@PostMapping(value = "agregar", consumes = "application/json", produces = "application/json")
	public List<Curso> agregarCurso(@RequestBody Curso curso) {
		return service.agregarCurso(curso);
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