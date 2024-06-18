package es.laspalmeras.padel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.laspalmeras.padel.models.Partido;
import es.laspalmeras.padel.services.CampeonatoService;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {
    @Autowired
    private CampeonatoService campeonatoService;

    // Otros endpoints...

    @PostMapping("/{partidoId}/registrar-resultado")
    public ResponseEntity<Partido> registrarResultado(@PathVariable Long partidoId, @RequestBody Partido resultado) {
        Partido partidoActualizado = campeonatoService.registrarResultadoPartido(partidoId, resultado);
        return ResponseEntity.ok(partidoActualizado);
    }
}