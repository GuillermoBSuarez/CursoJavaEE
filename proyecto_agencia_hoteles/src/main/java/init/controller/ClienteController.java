package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import init.model.Hotel;
import init.service.interfaces.ClienteService;

@Controller
public class ClienteController {
	@Autowired
	ClienteService service;

	@GetMapping(value = "clienteUP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> clienteUP(@RequestParam("usuario") String usuario,
											 @RequestParam("password") String password) {
		return new ResponseEntity<Hotel>( service.clienteUP(usuario, password), HttpStatus.OK );
	}
	
	@GetMapping(value = "clienteU", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> clienteU(@RequestParam("usuario") String usuario) {
		return new ResponseEntity<Hotel>( service.clienteU(usuario), HttpStatus.OK );
	}
	
	@PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> alta(@RequestBody Hotel cliente) {
		return new ResponseEntity<Hotel>( service.alta(cliente), HttpStatus.OK );
	}
}