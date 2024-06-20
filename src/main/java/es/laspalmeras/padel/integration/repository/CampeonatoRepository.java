package es.laspalmeras.padel.integration.repository;

import es.laspalmeras.padel.business.service.model.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
}