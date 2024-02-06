package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dtos.ClienteDTO;
import dtos.LibroDTO;
import jakarta.servlet.http.HttpSession;
import service.interfaces.ClienteService;
import service.interfaces.LibroService;
import service.interfaces.VentaService;

@Controller
public class libreriaController {
	@Autowired
	ClienteService clienteService;
	@Autowired
	LibroService libroService;
	@Autowired
	VentaService ventaService;
	
	@PostMapping(value = "agregarCliente")
	public String agregarCliente(@ModelAttribute ClienteDTO cliente, Model model) {
		if (!clienteService.agregar(cliente)) {
			model.addAttribute("mensajeError", cliente.getUsuario() + " ya existe.");
			return "nuevo";
		}
		return "login";
	}
	
	@PostMapping(value = "buscarCliente")
	public String buscarCliente(@RequestParam("usuario") String usuario, @RequestParam("password") String password, Model model, HttpSession sesion) {
		if (clienteService.buscar(usuario, password) != null) {
			sesion.setAttribute("cliente", clienteService.buscar(usuario, password));
			return "menu";
		}
		model.addAttribute("mensajeError", usuario + " o contraseña incorectos.");
		return "login";
	}
	
	@GetMapping(value = "catalogo")
	public String catalogo(Model model) {
		model.addAttribute("temas", libroService.getTemas());
		return "visor";
	}
	
	@GetMapping(value = "miscompras")
	public String compras(Model model, HttpSession sesion) {
		ClienteDTO clienteDTO = (ClienteDTO) sesion.getAttribute("cliente");
		model.addAttribute("comprascliente", ventaService.getVentasCliente(clienteDTO.getUsuario()));
		return "miscompras";
	}
	
	@GetMapping(value = "comprar")
	public String comprar(HttpSession sesion) {
		ClienteDTO cliente = (ClienteDTO) sesion.getAttribute("cliente");
		List<LibroDTO> libros = (List<LibroDTO>) sesion.getAttribute("carrito");
		ventaService.registarCompra(cliente.getUsuario(), libros);
		
		// Forzamos fin de sesion
		sesion.invalidate();
		return "login";
	}
	
	
	/* Peticiones AJAX
	   --------------- */

	@GetMapping(value = "getLibrosTema", produces = "application/json")
	public @ResponseBody List<LibroDTO> getLibrosTema(@RequestParam("idTema") int idTema) {
		return libroService.getLibrosTema(idTema);
	}

	// = AgregarController de Ej. 44
	@GetMapping(value = "agregar", produces="application/json")
	public @ResponseBody List<LibroDTO> agregar(@RequestParam("isbn") int isbn, HttpSession sesion) {
		List<LibroDTO> carrito = new ArrayList<>(); 
		if (sesion.getAttribute("carrito") != null)
			carrito = (List<LibroDTO>) sesion.getAttribute("carrito");
		carrito.add(libroService.getLibro(isbn));
		sesion.setAttribute("carrito", carrito);
		return carrito;
	}
		
	// = QuitarCarritoController de Ej. 44
	@GetMapping(value = "quitar")
	public @ResponseBody List<LibroDTO> quitar(@RequestParam("posicion") int pos, HttpSession sesion) {
		List<LibroDTO> carrito = new ArrayList<>(); 
		if (sesion.getAttribute("carrito") != null) {
			carrito = (List<LibroDTO>) sesion.getAttribute("carrito");
			carrito.remove(pos);
		}
		sesion.setAttribute("carrito", carrito);
		return carrito;
	}
}