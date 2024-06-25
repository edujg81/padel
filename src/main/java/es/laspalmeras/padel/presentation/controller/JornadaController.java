package es.laspalmeras.padel.presentation.controller;

import es.laspalmeras.padel.business.service.JornadaService;
import es.laspalmeras.padel.business.service.model.Jornada;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/jornadas")
@Tag(name = "Gestión de Jornada", description = "Operaciones pertenecientes a jornadas en Club de Padel")
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    @Operation(summary = "Crear jornada de un campeonato")
    @PostMapping
    public ResponseEntity<Jornada> createJornada(@RequestParam Long campeonatoId, @RequestParam LocalDate fechaInicio) {
        Jornada jornada = jornadaService.createJornada(campeonatoId, fechaInicio);
        return ResponseEntity.ok(jornada);
    }

    @Operation(summary = "Obtener todas las jornadas")
    @GetMapping
    public List<Jornada> getAllJornadas() {
        return jornadaService.findAllJornadas();
    }

    @Operation(summary = "Obtener jornada por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Jornada> getJornadaById(@PathVariable Long id) {
        Jornada jornada = jornadaService.findJornadaById(id);
        return ResponseEntity.ok(jornada);
    }

    @Operation(summary = "Obtener jornadas de un campeonato")
    @GetMapping("/campeonato/{campeonatoId}")
    public List<Jornada> getJornadasByCampeonato(@PathVariable Long campeonatoId) {
        return jornadaService.findJornadasByCampeonato(campeonatoId);
    }
    
    @Operation(summary = "Borrar jornada por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJornada(@PathVariable Long id) {
        jornadaService.deleteJornada(id);
        return ResponseEntity.noContent().build();
    }
}