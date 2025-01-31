package es.laspalmeras.padel.inscripcion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.inscripcion.model.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
	
	boolean existsByCampeonatoIdAndJugadorId(Long campeonatoId, Long jugadorId);

    long countByCampeonatoId(Long campeonatoId);

    List<Inscripcion> findByCampeonatoId(Long campeonatoId);

    List<Inscripcion> findByJugadorId(Long jugadorId);

	List<Inscripcion> findByJugadorIdAndCampeonatoYearAndCampeonatoCategoria(Long jugadorId, Integer year, String categoria);

	Optional<Inscripcion> findByCampeonatoIdAndJugadorId(Long campeonatoId, Long jugadorId);
	
}
