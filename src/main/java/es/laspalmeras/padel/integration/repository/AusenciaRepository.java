package es.laspalmeras.padel.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.business.service.model.Ausencia;

@Repository
public interface AusenciaRepository extends JpaRepository<Ausencia, Long> {

	List<Ausencia> findByPartidoId(Long partidoId);

}
