package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import init.model.Libro;
import init.service.interfaces.LibroService;

@CrossOrigin("*")
@Controller
public class libreriaController {
	@Autowired
	LibroService service;
	
	@GetMapping(value = "libros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> libros() {
		return new ResponseEntity<>( service.libros(), HttpStatus.OK );
	}
	
	@GetMapping(value = "tematicas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> tematicas() {
		return new ResponseEntity<>( service.tematicas(), HttpStatus.OK );
	}
	
	@GetMapping(value = "libros/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> libro(@PathVariable("isbn") int isbn) {
		Libro libro = service.libro(isbn);
		if (libro != null) return new ResponseEntity<>( service.libro(isbn), HttpStatus.OK );
		return new ResponseEntity<>( HttpStatus.NOT_FOUND );
	}
	
	@PostMapping(value = "alta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alta(@RequestBody Libro libro) {
		if ( service.alta(libro) ) return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}