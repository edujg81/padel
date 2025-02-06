package es.laspalmeras.padel.controller;

import java.util.List;
import java.util.Optional;

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

import es.laspalmeras.padel.dto.CampeonatoDTO;
import es.laspalmeras.padel.service.CampeonatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/campeonatos")
@Tag(name = "Gestión de Campeonato", description = "Operaciones pertenecientes a campeonatos en Club de Padel")
public class CampeonatoController {

    private CampeonatoService campeonatoService;
    
    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    /**
     * Crear un nuevo campeonato.
     * 
     * @param campeonato DTO del campeonato a crear.
     * @return DTO del campeonato creado.
     */
    @Operation(summary = "Crear un nuevo campeonato")
    @PostMapping
    public ResponseEntity<CampeonatoDTO> createCampeonato(@RequestBody CampeonatoDTO campeonato) {
        CampeonatoDTO nuevoCampeonato = campeonatoService.createCampeonato(campeonato);
        return ResponseEntity.ok(nuevoCampeonato);
    }

    /**
     * Obtener todos los campeonatos.
     * 
     * @return Lista de DTOs de campeonatos.
     */
    @Operation(summary = "Obtener todos los campeonatos")
    @GetMapping
    public List<CampeonatoDTO> getAllCampeonatos() {
        return campeonatoService.findAllCampeonatos();
    }

    /**
     * Obtener un campeonato por ID.
     * 
     * @param id ID del campeonato.
     * @return DTO del campeonato.
     */
    @Operation(summary = "Obtener un campeonato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoDTO> getCampeonatoById(@PathVariable Long id) {
        Optional<CampeonatoDTO> campeonato = campeonatoService.findCampeonatoById(id);
        return campeonato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Actualizar un campeonato por ID.
     * 
     * @param id ID del campeonato.
     * @param campeonatoDetails Detalles del campeonato a actualizar.
     * @return DTO del campeonato actualizado.
     */
    @Operation(summary = "Actualizar un campeonato por ID")
    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoDTO> updateCampeonato(@PathVariable Long id, @RequestBody CampeonatoDTO campeonatoDetails) {
        CampeonatoDTO updatedCampeonato = campeonatoService.updateCampeonato(id, campeonatoDetails);
        return ResponseEntity.ok(updatedCampeonato);
    }

    /**
     * Borrar un campeonato por ID.
     * 
     * @param id ID del campeonato.
     * @return Respuesta sin contenido.
     */
    @Operation(summary = "Borrar un campeonato por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampeonato(@PathVariable Long id) {
        campeonatoService.deleteCampeonato(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Cambiar estado de un campeonato.
     * 
     * @param id ID del campeonato.
     * @param nuevoEstado Nuevo estado del campeonato.
     * @return Respuesta sin contenido.
     */
    @Operation(summary = "Cambiar estado de un campeonato")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstadoCampeonato(@PathVariable Long id, @RequestParam String nuevoEstado) {
        campeonatoService.cambiarEstadoCampeonato(id, nuevoEstado);
        return ResponseEntity.noContent().build();
    }
}