package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {
	List<Clasificacion> findByCampeonatoId(Long campeonatoId);
	List<Clasificacion> findByCampeonatoIdOrderByPuntosDesc(Long campeonatoId);
    Clasificacion findByCampeonatoIdAndJugadorId(Long campeonatoId, Long jugadorId);
}