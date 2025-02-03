package es.laspalmeras.padel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.laspalmeras.padel.dto.JugadorDTO;
import es.laspalmeras.padel.service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/jugadores")
@Tag(name = "Gestión de Jugador", description = "Operaciones pertenecientes a jugadores registrados en Club de Padel")
public class JugadorController {

	@Autowired
	private JugadorService jugadorService;

	/**
	 * Crear un nuevo jugador.
	 * 
	 * @param jugador DTO del jugador a crear.
	 * @return ID del jugador creado.
	 */
	@Operation(summary = "Crear un nuevo jugador")
	@PostMapping
	public ResponseEntity<Long> createJugador(@Validated @RequestBody JugadorDTO jugador) {
		return ResponseEntity
		        .created(URI.create("/jugadores/" + jugador.getId()))
		        .body(jugadorService.create(jugador));
    }

	/**
	 * Obtener un jugador por ID.
	 * 
	 * @param id ID del jugador.
	 * @return DTO del jugador.
	 */
	@Operation(summary = "Obtener un jugador por ID")
	@GetMapping("/{id}")
	public ResponseEntity<JugadorDTO> getJugadorById(@PathVariable Long id) {
		Optional<JugadorDTO> jugador = jugadorService.getJugadorById(id);
        return jugador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	/**
	 * Obtener todos los jugadores.
	 * 
	 * @return Lista de DTOs de jugadores.
	 */
	@Operation(summary = "Obtener todos los jugadores")
	@GetMapping
	public List<JugadorDTO> getAllJugadores() {
		return jugadorService.getAllJugadores();
	}
	
	/**
	 * Actualizar un jugador por ID.
	 * 
	 * @param id ID del jugador.
	 * @param jugadorDetails Detalles del jugador a actualizar.
	 * @return DTO del jugador actualizado.
	 */
	@Operation(summary = "Actualizar un jugador por ID")
	@PutMapping("/{id}")
	public ResponseEntity<JugadorDTO> updateJugador(@PathVariable Long id, @RequestBody JugadorDTO jugadorDetails) {
		JugadorDTO updatedJugador = jugadorService.updateJugador(id, jugadorDetails);
		return ResponseEntity.ok(updatedJugador);
	}
	
	/**
	 * Borrar un jugador por ID.
	 * 
	 * @param id ID del jugador.
	 * @return Respuesta sin contenido.
	 */
	@Operation(summary = "Borrar un jugador por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
		jugadorService.deleteJugador(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Dar de baja un jugador por ID.
	 * 
	 * @param id ID del jugador.
	 * @return Respuesta sin contenido.
	 */
	@Operation(summary = "Dar de baja un jugador por ID")
	@PutMapping("/baja/{id}")
    public ResponseEntity<Void> darDeBajaJugadorPorId(@PathVariable Long id) {
        jugadorService.darDeBajaJugadorPorId(id);
        return ResponseEntity.noContent().build();
    }

	/**
	 * Dar de baja un jugador por DNI.
	 * 
	 * @param dni DNI del jugador.
	 * @return Respuesta sin contenido.
	 */
	@Operation(summary = "Dar de baja un jugador por DNI")
    @PutMapping("/baja/dni/{dni}")
    public ResponseEntity<Void> darDeBajaJugadorPorDni(@PathVariable String dni) {
        jugadorService.darDeBajaJugadorPorDni(dni);
        return ResponseEntity.noContent().build();
    }

	/**
	 * Borrar un jugador tras 5 años en baja.
	 * 
	 * @return Respuesta sin contenido.
	 */
	@Operation(summary = "Borrar un jugador tras 5 años en baja")
	@DeleteMapping("/eliminarBajaCincoAnios")
    public ResponseEntity<Void> eliminarJugadoresBajaMasDeCincoAnios() {
        jugadorService.eliminarJugadoresBajaMasDeCincoAnios();
        return ResponseEntity.noContent().build();
    }
	
	/**
	 * Obtener jugadores disponibles para campeonato.
	 * 
	 * @param campeonatoId ID del campeonato.
	 * @return Lista de DTOs de jugadores disponibles.
	 */
	@Operation(summary = "Obtener jugadores disponibles para campeonato(ID)")
    @GetMapping("/disponibles/{campeonatoId}")
    public List<JugadorDTO> getJugadoresDisponiblesParaCampeonato(@PathVariable Long campeonatoId) {
		return jugadorService.getJugadoresDisponiblesParaCampeonato(campeonatoId);
	}
}