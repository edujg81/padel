package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.models.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}