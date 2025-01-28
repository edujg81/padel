package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {
	List<Clasificacion> findByCampeonatoId(Long campeonatoId);
	List<Clasificacion> findByCampeonatoIdOrderByPosicionAsc(Long campeonatoId);
	@Query("SELECT c FROM Clasificacion c WHERE c.campeonato.id = :campeonatoId " +
	           "ORDER BY c.puntos DESC, " +
	           "c.partidosGanados DESC, " +
	           "c.partidosPerdidos ASC, " +
	           "c.setsGanados DESC, " +
	           "c.setsPerdidos ASC, " +
	           "c.juegosGanados DESC, " +
	           "c.juegosPerdidos ASC")
	List<Clasificacion> findClasificacionCompletaOrdenada(@Param("campeonatoId") Long campeonatoId);
    Clasificacion findByCampeonatoIdAndJugadorId(Long campeonatoId, Long jugadorId);
    
    default List<Clasificacion> findOrdenNatural(Long campeonatoId) {
        return findClasificacionCompletaOrdenada(campeonatoId).stream()
            .sorted(Comparator.comparingInt(Clasificacion::getPosicion))
            .toList();
    }
}