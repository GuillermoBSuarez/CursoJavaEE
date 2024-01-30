package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Producto;
import service.implementations.ProductoServiceImpl;
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
	
	@GetMapping(value = "buscar")
	public String buscar(@RequestParam("categoria") String categoria, Model model) {
		model.addAttribute("productos", service.buscar(categoria));
		return "productos";
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