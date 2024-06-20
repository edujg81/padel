package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
}
