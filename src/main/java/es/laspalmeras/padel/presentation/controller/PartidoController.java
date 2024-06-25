package es.laspalmeras.padel.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.laspalmeras.padel.business.service.PartidoService;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping
    public List<Partido> getAllPartidos() {
        return partidoService.findAllPartidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partido> getPartidoById(@PathVariable Long id) {
        Partido partido = partidoService.findPartidoById(id);
        return ResponseEntity.ok(partido);
    }

    @GetMapping("/jornada/{jornadaId}")
    public List<Partido> getPartidosByJornada(@PathVariable Long jornadaId) {
        return partidoService.findPartidosByJornada(jornadaId);
    }

    @PostMapping("/jornada/{jornadaId}")
    public ResponseEntity<List<Partido>> createPartidosForJornada(@PathVariable Long jornadaId, @RequestBody List<Jugador> jugadores) {
        List<Partido> partidos = partidoService.createPartidosForJornada(jornadaId, jugadores);
        return ResponseEntity.ok(partidos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partido> updatePartido(@PathVariable Long id, @RequestBody Partido partidoDetails) {
        Partido updatedPartido = partidoService.updatePartido(id, partidoDetails);
        return ResponseEntity.ok(updatedPartido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartido(@PathVariable Long id) {
        partidoService.deletePartido(id);
        return ResponseEntity.noContent().build();
    }
}
