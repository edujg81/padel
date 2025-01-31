package es.laspalmeras.padel.jornada;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {
	
	boolean existsByCampeonatoIdAndFechaInicio(Long campeonatoId, LocalDate fechaInicio);

    long countByCampeonatoId(Long campeonatoId);

    List<Jornada> findByCampeonatoId(Long campeonatoId);
	
}