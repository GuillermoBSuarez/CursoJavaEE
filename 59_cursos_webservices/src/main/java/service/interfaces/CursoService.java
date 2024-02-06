package service.interfaces;

import java.util.List;

import model.Curso;

public interface CursoService {
	
	List<Curso> cursos();
	Curso curso(int idCurso);
	List<Curso> cursosPrecios(double precioMin, double precioMax);
	List<Curso> agregarCurso(Curso curso);
	Curso eliminarCurso(String denominacion);
	void actualizarPrecio(String denominacion, double porcentaje);
}