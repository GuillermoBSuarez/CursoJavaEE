package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Cliente;
import init.service.interfaces.ClienteService;

@CrossOrigin("*")
@RestController
public class ClienteController {
	@Autowired
	ClienteService service;

	@PostMapping(value = "clienteUP",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> clienteUP(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>( service.clienteUP(cliente.getUsuario(),
															  cliente.getPassword()),
											HttpStatus.OK );
	}
	
	@GetMapping(value = "clienteU", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> clienteU(@RequestParam("usuario") String usuario) {
		return new ResponseEntity<Cliente>( service.clienteU(usuario), HttpStatus.OK );
	}
	
	@PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alta(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>( service.alta(cliente), HttpStatus.OK );
	}
}