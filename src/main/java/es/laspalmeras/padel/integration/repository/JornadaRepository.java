package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {
}