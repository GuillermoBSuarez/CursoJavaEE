package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Producto;
import service.interfaces.ProductoService;

@Controller
public class productoController {
	@Autowired
	ProductoService service;
	
	@PostMapping(value = "agregar")
	public String agregar(@ModelAttribute Producto producto, Model model) {
		if (!service.agregar(producto)) model.addAttribute("mensajeError", producto.getNombre()+" ya existe.");
		return "menu";
	}
	
	@GetMapping(value = "buscar", produces = "application/json")
	// la segunda parte (produces) no es necesaria en este caso porque es json por defecto, pero pa' que lo veas
	public @ResponseBody List<Producto> buscar(@RequestParam("categoria") String categoria, Model model) {
		return service.buscar(categoria);
	}
	
	@GetMapping(value = "actualizar")
	public String actualizar(@RequestParam("nombre") String nombre, @RequestParam("precio") Double precio) {
		service.actualizar(nombre, precio);
		return "menu";
	}
	
	@PostMapping(value = "eliminar")
	public String eliminar(@RequestParam("nombre") String nombre, Model model) {
		if (service.eliminar(nombre) == null) model.addAttribute("mensajeError", "No se ha encontrado " + nombre);
		return "menu";
	}
}