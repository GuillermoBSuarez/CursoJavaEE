package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Vuelo;
import init.service.interfaces.VueloService;

@CrossOrigin("*")
@RestController
public class VueloController {
	
	@Autowired
	VueloService service;

	@GetMapping(value = "vuelo/{idVuelo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vuelo> vuelo(@PathVariable("idVuelo") int idVuelo) {
		return new ResponseEntity<Vuelo>(service.vuelo(idVuelo), HttpStatus.OK);
	}

	@GetMapping(value = "vuelos/{destino}/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vuelo>> vuelos(@PathVariable("destino") String destino,
											  @PathVariable("plazas") int plazas) {
		return new ResponseEntity<List<Vuelo>>(service.vuelos(destino, plazas), HttpStatus.OK);
	}
	
	@PutMapping(value = "vueloupdate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vuelo> vueloUpdate(@RequestParam("idVuelo") int idVuelo,
											 @RequestParam("plazas") int plazas) {
		return new ResponseEntity<Vuelo>( service.vueloUpdate(idVuelo, plazas), HttpStatus.OK );
	}
}