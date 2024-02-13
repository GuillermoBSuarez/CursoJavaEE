package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import init.model.Curso;

public interface CursoDAO extends JpaRepository<Curso, Integer> {

	Curso findByIdCurso(int idCurso);
	
	List<Curso> findByPrecioBetween (double precioMin, double precioMax);
		
	/* Para eliminar por denominación no podemos usar sólo el deleteBy porque
	es void y nos han pedido que devuelva el Curso, así que usamos dos: */
	Curso findByDenominacion(String denominacion);
	@Transactional
	@Modifying
	void deleteByDenominacion(String denoninacion);
	
	@Transactional
	@Modifying
	@Query("update Curso c set c.precio=c.precio*(100+?1)/100 where c.denominacion=?2")
	void updatePrecio(int porcentaje, String denominacion);
}