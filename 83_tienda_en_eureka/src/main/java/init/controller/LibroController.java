package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.Libro;
import init.service.interfaces.LibroService;

@CrossOrigin("*")
@RestController
public class LibroController {
	@Autowired
	LibroService service;
	
	@GetMapping(value = "tematicas", produces = "application/json")
	public ResponseEntity<List<String>> tematicas() {
		return new ResponseEntity<>( service.tematicas(), HttpStatus.OK );
	}

	@GetMapping(value="libros/{tematica}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> librosTematica(@PathVariable("tematica") String tema){
		return new ResponseEntity<>( service.librosTematica(tema), HttpStatus.OK );
	}
}