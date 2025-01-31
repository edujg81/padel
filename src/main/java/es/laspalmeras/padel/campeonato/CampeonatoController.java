package es.laspalmeras.padel.campeonato;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/campeonatos")
@Tag(name = "Gestión de Campeonato", description = "Operaciones pertenecientes a campeonatos en Club de Padel")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @Operation(summary = "Crear un nuevo campeonato")
    @PostMapping
    public ResponseEntity<CampeonatoDTO> createCampeonato(@RequestBody CampeonatoDTO campeonato) {
        CampeonatoDTO nuevoCampeonato = campeonatoService.createCampeonato(campeonato);
        return ResponseEntity.ok(nuevoCampeonato);
    }

    @Operation(summary = "Obtener todos los campeonatos")
    @GetMapping
    public List<CampeonatoDTO> getAllCampeonatos() {
        return campeonatoService.findAllCampeonatos();
    }

    @Operation(summary = "Obtener un campeonato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoDTO> getCampeonatoById(@PathVariable Long id) {
        Optional<CampeonatoDTO> campeonato = campeonatoService.findCampeonatoById(id);
        return campeonato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Actualizar un campeonato por ID")
    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoDTO> updateCampeonato(@PathVariable Long id, @RequestBody CampeonatoDTO campeonatoDetails) {
        CampeonatoDTO updatedCampeonato = campeonatoService.updateCampeonato(id, campeonatoDetails);
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