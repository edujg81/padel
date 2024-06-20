package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
