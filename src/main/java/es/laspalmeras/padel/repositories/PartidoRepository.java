package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.laspalmeras.padel.models.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
}