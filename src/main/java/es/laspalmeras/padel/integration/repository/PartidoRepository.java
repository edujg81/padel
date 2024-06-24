package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
	
}
