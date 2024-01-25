package controller;

import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Resultado;
import service.interfaces.BuscadorService;

@Controller
public class buscadorController {
	@Autowired
	BuscadorService buscadorService;

	@GetMapping(value = "buscar")
	public String buscar(@RequestParam("tematica") String tematica, Model model) {
		List<Resultado> resultados = buscadorService.buscar(tematica);
		model.addAttribute("resultados", resultados); // equivale a Request.SetAttribute(...)
		return "resultados"; // página a la que debe dirigirnos el FrontController
	}
	
	@PostMapping(value = "agregar")
	public String agregar(@ModelAttribute Resultado resultado) {
		/* Con RequestParam habría que usarlo tres veces, una para cada parámetro de Resultado,
		porque RequestParam son los parámetros para construir la url, no puede ir un objeto.
		Con ModelAttribute recibe el objeto entero y Spring asocia cada parámetro a cada atributo.
		Para ello, necesita que el bean tenga un constructor vacío, que lo tenemos con la etiqueta
		NoArgsConstructor de Lombok. Y Spring invoca a los métodos set de cada atributo. */
		buscadorService.agregar(resultado);
		return "menu";
	}

	
	
	/* NAVEGACIONES ESTÁTICAS
	   ---------------------- */

	@GetMapping(value = "toBuscar")		// se puede usar un array de valores que entrarán en este método
	public String toBuscar() {
		return "buscar";
	}

	@GetMapping(value = "toNuevaEntrada")
	public String toNuevaEntrada() {
		return "nuevaentrada";
	}	
	@GetMapping(value = "/")
	public String bienvenida() {
		return "menu";
	}
}