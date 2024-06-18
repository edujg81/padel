package es.laspalmeras.padel.repositories;

import es.laspalmeras.padel.models.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
}