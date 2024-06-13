package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.laspalmeras.padel.models.Championship;
import es.laspalmeras.padel.models.Ranking;
import es.laspalmeras.padel.models.Player;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Ranking findByChampionshipAndPlayer(Championship championship, Player player);
}