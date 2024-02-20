package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import init.model.Producto;
import init.service.interfaces.ProductoService;

@CrossOrigin("*")
@RestController
public class ProductoController {
	
	@Autowired
	ProductoService service;
	
	@GetMapping(value = "productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> productos() {
		return new ResponseEntity<>( service.productos(), HttpStatus.OK );
	}

	@PutMapping(value = "actualizarStock/{codigoProducto}/{unidades}")
	public ResponseEntity<Void> actualizarStock(@PathVariable("codigoProducto") int codigoProducto, @PathVariable("unidades") int unidades) {
		service.actualizarStock(codigoProducto, unidades);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "precioProducto/{codigoProducto}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> precioProducto(@PathVariable("codigoProducto") int codigoProducto){
		return new ResponseEntity<>( Double.toString( service.precioProducto(codigoProducto)), HttpStatus.OK );
	}
}