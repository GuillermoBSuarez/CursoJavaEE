package service.interfaces;

import java.util.List;

import init.exceptions.CursoExistenteException;
import init.model.Curso;

public interface CursoService {
	
	List<Curso> cursos();
	Curso curso(int idCurso);
	List<Curso> cursosPrecios(double precioMin, double precioMax);
	List<Curso> agregarCurso(Curso curso) throws CursoExistenteException;
	Curso eliminarCurso(String denominacion);
	void actualizarPrecio(int porcentaje, String denominacion);
}