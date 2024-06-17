package es.laspalmeras.padel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.laspalmeras.padel.models.Campeonato;
import es.laspalmeras.padel.models.Substitution;
import es.laspalmeras.padel.services.CampeonatoService;

import java.util.List;

@RestController
@RequestMapping("/championships")
public class CampeonatoController {
    @Autowired
    private CampeonatoService championshipService;

    @GetMapping
    public List<Campeonato> getAllChampionships() {
        return championshipService.getAllChampionships();
    }

    @GetMapping("/{id}")
    public Campeonato getChampionshipById(@PathVariable Long id) {
        return championshipService.getChampionshipById(id);
    }

    @PostMapping
    public Campeonato saveChampionship(@RequestBody Campeonato championship) {
        return championshipService.saveChampionship(championship);
    }

    @PostMapping("/{id}/generate-matches")
    public void generateMatches(@PathVariable Long id) {
        Campeonato championship = championshipService.getChampionshipById(id);
        championshipService.generateMatches(championship);
    }

    @PostMapping("/{id}/matches/{matchId}/result")
    public void updateMatchResult(@PathVariable Long id, @PathVariable Long matchId,
                                  @RequestParam int scoreTeam1, @RequestParam int scoreTeam2,
                                  @RequestBody List<Substitution> substitutions) {
        championshipService.updateMatchResult(matchId, scoreTeam1, scoreTeam2, substitutions);
    }

    @DeleteMapping("/{id}")
    public void deleteChampionship(@PathVariable Long id) {
        championshipService.deleteChampionship(id);
    }
}