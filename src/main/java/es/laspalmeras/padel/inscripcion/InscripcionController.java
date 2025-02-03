package es.laspalmeras.padel.inscripcion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.laspalmeras.padel.exception.ResourceNotFoundException;
import es.laspalmeras.padel.inscripcion.model.Inscripcion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/inscripciones")
@Tag(name = "Gestión de Inscripcion", description = "Operaciones pertenecientes a inscripciones en Club de Padel")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    /**
     * Inscribir jugador a campeonato.
     * 
     * @param campeonatoId ID del campeonato.
     * @param jugadorId ID del jugador.
     * @return Inscripción creada.
     */
    @Operation(summary = "Inscribir jugador a campeonato")
    @PostMapping
    public ResponseEntity<Inscripcion> inscribirJugador(@RequestParam Long campeonatoId, @RequestParam Long jugadorId) {
        Inscripcion inscripcion = inscripcionService.inscribirJugador(campeonatoId, jugadorId);
        return ResponseEntity.ok(inscripcion);
    }

    /**
     * Desinscribir jugador de campeonato.
     * 
     * @param campeonatoId ID del campeonato.
     * @param jugadorId ID del jugador.
     * @return Respuesta sin contenido.
     */
    @Operation(summary = "Desinscribir jugador de campeonato")
    @DeleteMapping("/{campeonatoId}/{jugadorId}")
    public ResponseEntity<Void> desinscribirJugador(@PathVariable Long campeonatoId, @PathVariable Long jugadorId) {
        inscripcionService.desinscribirJugador(campeonatoId, jugadorId);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Borrar inscripción por ID.
     * 
     * @param id ID de la inscripción.
     * @return Respuesta sin contenido.
     */
    @Operation(summary = "Borrar inscripción por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        inscripcionService.deleteInscripcion(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Obtener todas las inscripciones.
     * 
     * @return Lista de inscripciones.
     */
    @Operation(summary = "Obtener todas las inscripciones")
    @GetMapping
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionService.findAllInscripciones();
    }

    /**
     * Obtener inscripción por ID.
     * 
     * @param id ID de la inscripción.
     * @return Inscripción encontrada.
     */
    @Operation(summary = "Obtener inscripción por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> getInscripcionById(@PathVariable Long id) {
        Inscripcion inscripcion = inscripcionService.findInscripcionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id: " + id));
        return ResponseEntity.ok(inscripcion);
    }

    /**
     * Obtener inscripciones por campeonato(ID).
     * 
     * @param campeonatoId ID del campeonato.
     * @return Lista de inscripciones.
     */
    @Operation(summary = "Obtener inscripciones por campeonato(ID)")
    @GetMapping("/campeonato/{campeonatoId}")
    public List<Inscripcion> getInscripcionesByCampeonato(@PathVariable Long campeonatoId) {
        return inscripcionService.findInscripcionesByCampeonato(campeonatoId);
    }

    /**
     * Obtener inscripciones por jugador(ID).
     * 
     * @param jugadorId ID del jugador.
     * @return Lista de inscripciones.
     */
    @Operation(summary = "Obtener inscripciones por jugador(ID)")
    @GetMapping("/jugador/{jugadorId}")
    public List<Inscripcion> getInscripcionesByJugador(@PathVariable Long jugadorId) {
        return inscripcionService.findInscripcionesByJugador(jugadorId);
    }
    
    /**
     * Obtener inscripción por campeonato(ID) y jugador(ID).
     * 
     * @param campeonatoId ID del campeonato.
     * @param jugadorId ID del jugador.
     * @return Inscripción encontrada.
     */
    @Operation(summary = "Obtener inscripción por campeonato(ID) y jugador(ID)")
    @GetMapping("/campeonato/{campeonatoId}/jugador/{jugadorId}")
    public ResponseEntity<Inscripcion> getInscripcionByCampeonatoAndJugador(@PathVariable Long campeonatoId, @PathVariable Long jugadorId) {
        Inscripcion inscripcion = inscripcionService.findInscripcionByCampeonatoAndJugador(campeonatoId, jugadorId)
        		.orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada para Id de campeonato: " + campeonatoId + " e Id de jugador: " + jugadorId));
        return ResponseEntity.ok(inscripcion);
    }
}
