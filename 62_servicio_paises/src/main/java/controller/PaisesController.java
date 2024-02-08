package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Pais;
import service.interfaces.PaisesService;

@CrossOrigin("*")		// Para permitir las llamadas que haremos desde el Ej. 63
@RestController
public class PaisesController {
	@Autowired
	PaisesService service;

	@GetMapping(value = "continentes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes() {
		return service.continentes();
	}
	
	@GetMapping(value = "paises/{continente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> porContinente(@PathVariable("continente") String continente){
		return service.paisesContinente(continente);
	}
	
	@GetMapping(value = "maspoblado", produces = MediaType.APPLICATION_JSON_VALUE)
	public Pais masPoblado(){
		return service.paisMasPoblado();
	}
}