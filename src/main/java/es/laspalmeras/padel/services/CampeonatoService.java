package es.laspalmeras.padel.services;

import es.laspalmeras.padel.models.*;
import es.laspalmeras.padel.repositories.CampeonatoRepository;
import es.laspalmeras.padel.repositories.PartidoRepository;
import es.laspalmeras.padel.repositories.JugadorRepository;
import es.laspalmeras.padel.repositories.ClasificacionRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CampeonatoService {
    @Autowired
    private CampeonatoRepository championshipRepository;

    @Autowired
    private PartidoRepository matchRepository;

    @Autowired
    private JugadorRepository playerRepository;

    @Autowired
    private ClasificacionRepository rankingRepository;

    public List<Campeonato> getAllChampionships() {
        return championshipRepository.findAll();
    }

    public Campeonato getChampionshipById(Long id) {
        return championshipRepository.findById(id).orElse(null);
    }

    public Campeonato saveChampionship(Campeonato championship) {
        return championshipRepository.save(championship);
    }

    public void deleteChampionship(Long id) {
        championshipRepository.deleteById(id);
    }

    public void generateMatches(Campeonato championship) {
        List<Jugador> players = new ArrayList<>(championship.getPlayers());
        players.sort(Comparator.comparingInt(player -> player.getRanking().getPosition()));

        for (int i = 0; i < players.size(); i += 4) {
            Team team1 = new Team();
            team1.setPlayers(Set.of(players.get(i), players.get(i + 3)));
            Team team2 = new Team();
            team2.setPlayers(Set.of(players.get(i + 1), players.get(i + 2)));
            
            Partido match = new Partido();
            match.setTeam1(team1);
            match.setTeam2(team2);
            match.setChampionship(championship);
            match.setCourt("Court " + ((i / 4) + 1)); // Asigna una pista
            match.setDate(LocalDate.now().plusDays(i / 4)); // Asigna una fecha
            
            matchRepository.save(match);
        }
    }

    public void updateMatchResult(Long matchId, int scoreTeam1, int scoreTeam2, List<Substitution> substitutions) {
        Partido match = matchRepository.findById(matchId).orElse(null);
        if (match == null) {
            throw new RuntimeException("Match not found");
        }

        match.setScoreTeam1(scoreTeam1);
        match.setScoreTeam2(scoreTeam2);
        matchRepository.save(match);

        processSubstitutions(match, substitutions);
        updateRankings(match);
    }

    private void processSubstitutions(Partido match, List<Substitution> substitutions) {
        for (Substitution substitution : substitutions) {
            substitution.setMatch(match);
            if (substitution.getReason().equals("Ausencia")) {
                penalizeAbsentPlayer(substitution.getAbsentPlayer());
            } else if (substitution.getReason().equals("Lesión")) {
                // Manejar la lógica específica para lesiones si es necesario
            }

            if (match.getChampionship().getCategory().equals("Femenina")) {
                awardPointToSubstitutePlayer(substitution.getSubstitutePlayer());
            }
        }
    }

    private void penalizeAbsentPlayer(Jugador player) {
        Clasificacion ranking = rankingRepository.findByChampionshipAndPlayer(player.getChampionship(), player);
        ranking.setSetsLost(ranking.getSetsLost() + 2);
        ranking.setGamesLost(ranking.getGamesLost() + 12);
        rankingRepository.save(ranking);
    }

    private void awardPointToSubstitutePlayer(Jugador player) {
        Clasificacion ranking = rankingRepository.findByChampionshipAndPlayer(player.getChampionship(), player);
        ranking.setPoints(ranking.getPoints() + 1);
        rankingRepository.save(ranking);
    }

    private void updateRankings(Partido match) {
        // Actualiza los rankings de los jugadores en base al resultado del partido
        List<Jugador> team1Players = new ArrayList<>(match.getTeam1().getPlayers());
        List<Jugador> team2Players = new ArrayList<>(match.getTeam2().getPlayers());

        for (Jugador player : team1Players) {
            updatePlayerRanking(player, match.getScoreTeam1(), match.getScoreTeam2());
        }
        for (Jugador player : team2Players) {
            updatePlayerRanking(player, match.getScoreTeam2(), match.getScoreTeam1());
        }
    }

    private void updatePlayerRanking(Jugador player, int scoreWon, int scoreLost) {
        Clasificacion ranking = rankingRepository.findByChampionshipAndPlayer(player.getChampionship(), player);
        ranking.setMatchesPlayed(ranking.getMatchesPlayed() + 1);
        ranking.setGamesWon(ranking.getGamesWon() + scoreWon);
        ranking.setGamesLost(ranking.getGamesLost() + scoreLost);
        ranking.setSetsWon(ranking.getSetsWon() + (scoreWon >= 6 ? 1 : 0));
        ranking.setSetsLost(ranking.getSetsLost() + (scoreLost >= 6 ? 1 : 0));

        if (scoreWon > scoreLost) {
            ranking.setPoints(ranking.getPoints() + 2);
            ranking.setMatchesWon(ranking.getMatchesWon() + 1);
        } else {
            ranking.setMatchesLost(ranking.getMatchesLost() + 1);
        }

        rankingRepository.save(ranking);
    }
}