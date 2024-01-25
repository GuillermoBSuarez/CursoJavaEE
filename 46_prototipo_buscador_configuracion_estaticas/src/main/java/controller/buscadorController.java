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
		return "resultados";
	}
	
	@PostMapping(value = "agregar")
	public String agregar(@ModelAttribute Resultado resultado) {
		buscadorService.agregar(resultado);
		return "menu";
	}
}