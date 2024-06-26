package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {
    List<Clasificacion> findByCampeonatoId(Long campeonatoId);
    Clasificacion findByCampeonatoIdAndJugadorId(Long campeonatoId, Long jugadorId);
}
