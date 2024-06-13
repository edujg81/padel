package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.laspalmeras.padel.models.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}