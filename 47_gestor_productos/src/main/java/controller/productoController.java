package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Producto;
import service.interfaces.ProductoService;

@Controller
public class productoController {
	@Autowired
	ProductoService service;
	
	@GetMapping(value = "buscar")
	public String buscar(@RequestParam("categoria") String categoria, Model model) {
		List<Producto> productos = service.buscar(categoria);
		model.addAttribute("productos", productos);
		return "productos";
	}
	
	@PostMapping(value = "agregar")
	public String agregar(@ModelAttribute Producto producto) {
		service.agregar(producto);
		return "menu";
	}
	
	@GetMapping(value = "eliminar")
	public String eliminar(@RequestParam("nombre") String nombre) {
		service.eliminar(nombre);
		return "menu";
	}
}