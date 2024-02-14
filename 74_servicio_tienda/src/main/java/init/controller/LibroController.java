package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Libro;
import init.service.interfaces.LibroService;

@RestController
public class LibroController {
	@Autowired
	LibroService service;
	
	@GetMapping(value = "tematicas", produces = "application/json")
	public ResponseEntity<List<String>> tematicas() {
		return new ResponseEntity<>( service.tematicas(), HttpStatus.OK );
	}

	@GetMapping(value = "libros", produces = "application/json")
	public ResponseEntity<List<Libro>> libros(@RequestParam("tematica") String tematica) {
		return new ResponseEntity<>( service.libros(tematica), HttpStatus.OK );
	}
}