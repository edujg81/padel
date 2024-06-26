package es.laspalmeras.padel.presentation.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.laspalmeras.padel.business.service.JornadaService;
import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.model.Jornada;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
    public ResponseEntity<List<JornadaDTO>> getAllJornadas() {
    	return ResponseEntity.ok(jornadaService.findAllJornadas());
    }

    @Operation(summary = "Obtener jornada por ID")
    @GetMapping("/{id}")
    public ResponseEntity<JornadaDTO> getJornadaById(@PathVariable Long id) {
        Optional<JornadaDTO> jornada = jornadaService.findJornadaById(id);
        return jornada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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