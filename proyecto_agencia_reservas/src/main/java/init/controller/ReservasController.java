package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.dto.ReservaDTO;
import init.service.interfaces.ReservaService;

@RestController
public class ReservasController {
	
	@Autowired
	ReservaService service;

	@PostMapping(value = "alta/{plazas}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> alta(@RequestBody ReservaDTO reserva, @PathVariable("plazas") int plazas){
		return new ResponseEntity<Boolean> (service.alta(reserva, plazas), HttpStatus.OK); 
	}
	
	@GetMapping(value = "reservas/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservaDTO>> reservas(@PathVariable("usuario") String usuario) {
		return new ResponseEntity<List<ReservaDTO>> (service.reservas(usuario), HttpStatus.OK);
	}
}