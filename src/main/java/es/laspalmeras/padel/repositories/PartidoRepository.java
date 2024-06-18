package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.models.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
}