package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.CursoDAO;
import init.exceptions.CursoExistenteException;
import init.model.Curso;
import service.interfaces.CursoService;

@Service
public class CursoServiceImpl implements CursoService{
	
	@Autowired
	CursoDAO DAO;
	
	@Override
	public List<Curso> cursos() {
		return DAO.findAll();
	}

	@Override
	public Curso curso(int idCurso) {
		return DAO.findById(idCurso).orElse(null);
	}
	
	@Override
	public List<Curso> cursosPrecios(double precioMin, double precioMax) {
		return DAO.findByPrecioBetween(precioMin, precioMax);
	}
	
	@Override
	public List<Curso> agregarCurso(Curso curso) throws CursoExistenteException {
		// Modificación: sólo agregará tras comprobar que el curso no existe. Si existe...
		if (DAO.findByDenominacion(curso.getDenominacion()) != null)	// = si ya hay un curso con ese nombre
			throw new CursoExistenteException();
		DAO.save(curso);
		return DAO.findAll();
	}

	@Override
	public Curso eliminarCurso(String denominacion) {
		Curso curso = DAO.findByDenominacion(denominacion);
		if (curso != null) DAO.delete(curso);
		return curso;
	}

	@Override
	public void actualizarPrecio(int porcentaje, String denominacion) {
		DAO.updatePrecio(porcentaje, denominacion);
	}	
}