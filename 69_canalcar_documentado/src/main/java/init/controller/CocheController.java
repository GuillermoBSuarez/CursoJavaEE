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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin("*")
@RestController
public class CocheController {
	@Autowired
	CocheService service;
	
	@Operation(summary = "Alta de nuevos coches", description = "Recibe un json con los datos del nuevo coche y no devuelve nada. Sólo lo da de alta si la matrícula no existe.")
	@PostMapping(value = "alta", consumes = "application/json")
	public void alta(@Parameter(description = "JSON con los datos del coche.") @RequestBody Coche coche) {
		service.alta(coche);
	}
	
	@DeleteMapping(value = "eliminar", produces = "application/json")
	public Coche eliminar(@RequestParam("matricula") String matricula) {
		return service.eliminar(matricula);
	}
	
	@Operation(summary = "Coches por marca", description = "Recibe una cadena con una marca y devuelve un JSON con los coches de esa marca.")
	@GetMapping(value = "cochesmarca", produces = "application/json")
	public List<Coche> cochesMarca(@Parameter(description = "Marca de coches") @RequestParam("marca") String marca) {
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
