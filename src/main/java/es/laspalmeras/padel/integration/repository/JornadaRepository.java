package es.laspalmeras.padel.integration.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.model.Jornada;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {
	
	boolean existsByCampeonatoIdAndFechaInicio(Long campeonatoId, LocalDate fechaInicio);

    long countByCampeonatoId(Long campeonatoId);

    List<Jornada> findByCampeonatoId(Long campeonatoId);
	
}