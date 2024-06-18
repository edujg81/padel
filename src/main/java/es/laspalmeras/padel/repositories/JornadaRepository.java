package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.models.Jornada;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {
}