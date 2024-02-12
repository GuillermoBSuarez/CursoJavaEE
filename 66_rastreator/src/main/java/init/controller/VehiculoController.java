package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.interfaces.VehiculoService;
import init.model.Vehiculo;

@CrossOrigin("*")
@RestController
public class VehiculoController {
	@Autowired
	VehiculoService service;
	
	@GetMapping(value = "vehiculos/{kmsMin}/{kmsMax}", produces = "application/json")
	public List<Vehiculo> cochesRangoKms(@PathVariable("kmsMin") int kmsMin, @PathVariable("kmsMax") int kmsMax) {
		return service.cochesRangoKms(kmsMin, kmsMax);
	}
	
	@GetMapping(value = "vehiculos/{precio}", produces = "application/json")
	public List<Vehiculo> cochesPrecioMax(@PathVariable("precio") double precio) {
		return service.cochesPrecioMax(precio);
	}
}