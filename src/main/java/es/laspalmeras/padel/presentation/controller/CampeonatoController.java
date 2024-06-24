package es.laspalmeras.padel.presentation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.laspalmeras.padel.business.service.CampeonatoService;
import es.laspalmeras.padel.business.service.model.Campeonato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/campeonatos")
@Tag(name = "Gestión de Campeonato", description = "Operaciones pertenecientes a campeonatos en Club de Padel")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @Operation(summary = "Crear un nuevo campeonato")
    @PostMapping
    public ResponseEntity<Campeonato> createCampeonato(@RequestBody Campeonato campeonato) {
        Campeonato nuevoCampeonato = campeonatoService.createCampeonato(campeonato);
        return ResponseEntity.ok(nuevoCampeonato);
    }

    @Operation(summary = "Obtener todos los campeonatos")
    @GetMapping
    public List<Campeonato> getAllCampeonatos() {
        return campeonatoService.findAllCampeonatos();
    }

    @Operation(summary = "Obtener un campeonato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Campeonato> getCampeonatoById(@PathVariable Long id) {
        Optional<Campeonato> campeonato = campeonatoService.findCampeonatoById(id);
        return campeonato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Actualizar un campeonato por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> updateCampeonato(@PathVariable Long id, @RequestBody Campeonato campeonatoDetails) {
        Campeonato updatedCampeonato = campeonatoService.updateCampeonato(id, campeonatoDetails);
        return ResponseEntity.ok(updatedCampeonato);
    }

    @Operation(summary = "Borrar un campeonato por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampeonato(@PathVariable Long id) {
        campeonatoService.deleteCampeonato(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Cambiar estado de un campeonato")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstadoCampeonato(@PathVariable Long id, @RequestParam String nuevoEstado) {
        campeonatoService.cambiarEstadoCampeonato(id, nuevoEstado);
        return ResponseEntity.noContent().build();
    }
}