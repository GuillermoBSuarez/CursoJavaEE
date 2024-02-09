package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.interfaces.CocheService;
import init.model.Coche;

@CrossOrigin("*")
@RestController
public class CocheController {
	@Autowired
	CocheService service;
	
	@PostMapping(value = "alta", consumes = "application/json")
	public void alta(@RequestBody Coche coche) {
		service.alta(coche);
	}
	
	@DeleteMapping(value = "eliminar", produces = "application/json")
	public Coche eliminar(@RequestParam("matricula") String matricula) {
		return service.eliminar(matricula);
	}
	
	@GetMapping(value = "cochesmarca", produces = "application/json")
	public List<Coche> cochesMarca(@RequestParam("marca") String marca) {
		return service.cochesMarca(marca);
	}
	
	@GetMapping(value = "cochespreciomax", produces = "application/json")
	public List<Coche> cochesPrecioMax(@RequestParam("precio") double precio) {
		return service.cochesPrecioMax(precio);
	}
	
	@GetMapping(value = "coches", produces = "application/json")
	public List<Coche> coches() {
		return service.coches();
	}
	
	@PutMapping(value = "actualizar", consumes = "application/json")
	public void actualizar(@RequestBody Coche coche) {
		service.actualizar(coche);
	}
}
