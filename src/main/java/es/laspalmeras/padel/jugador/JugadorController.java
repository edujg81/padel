package es.laspalmeras.padel.jugador;

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

import es.laspalmeras.padel.jugador.model.dto.JugadorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/jugadores")
@Tag(name = "Gestión de Jugador", description = "Operaciones pertenecientes a jugadores registrados en Club de Padel")
public class JugadorController {

	@Autowired
	private JugadorService jugadorService;

	@Operation(summary = "Crear un nuevo jugador")
	@PostMapping
	public ResponseEntity<Long> createJugador(@Validated @RequestBody JugadorDTO jugador) {
//        Long id = jugadorService.create(jugador);
//        return ResponseEntity.ok(id);
		return ResponseEntity
		        .created(URI.create("/jugadores/" + jugador.getId()))
		        .body(jugadorService.create(jugador));
    }

	@Operation(summary = "Obtener un jugador por ID")
	@GetMapping("/{id}")
	public ResponseEntity<JugadorDTO> getJugadorById(@PathVariable Long id) {
		Optional<JugadorDTO> jugador = jugadorService.getJugadorById(id);
        return jugador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Obtener todos los jugadores")
	@GetMapping
	public List<JugadorDTO> getAllJugadores() {
		return jugadorService.getAllJugadores();
	}
	
	@Operation(summary = "Actualizar un jugador por ID")
	@PutMapping("/{id}")
	public ResponseEntity<JugadorDTO> updateJugador(@PathVariable Long id, @RequestBody JugadorDTO jugadorDetails) {
		JugadorDTO updatedJugador = jugadorService.updateJugador(id, jugadorDetails);
		return ResponseEntity.ok(updatedJugador);
	}
	
	@Operation(summary = "Borrar un jugador por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
		jugadorService.deleteJugador(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Dar de baja un jugador por ID")
	@PutMapping("/baja/{id}")
    public ResponseEntity<Void> darDeBajaJugadorPorId(@PathVariable Long id) {
        jugadorService.darDeBajaJugadorPorId(id);
        return ResponseEntity.noContent().build();
    }

	@Operation(summary = "Dar de baja un jugador por DNI")
    @PutMapping("/baja/dni/{dni}")
    public ResponseEntity<Void> darDeBajaJugadorPorDni(@PathVariable String dni) {
        jugadorService.darDeBajaJugadorPorDni(dni);
        return ResponseEntity.noContent().build();
    }

	@Operation(summary = "Borrar un jugador tras 5 años en baja")
	@DeleteMapping("/eliminarBajaCincoAnios")
    public ResponseEntity<Void> eliminarJugadoresBajaMasDeCincoAnios() {
        jugadorService.eliminarJugadoresBajaMasDeCincoAnios();
        return ResponseEntity.noContent().build();
    }
	
	@Operation(summary = "Obtener jugadores disponibles para campeonato(ID)")
    @GetMapping("/disponibles/{campeonatoId}")
    public List<JugadorDTO> getJugadoresDisponiblesParaCampeonato(@PathVariable Long campeonatoId) {
		return jugadorService.getJugadoresDisponiblesParaCampeonato(campeonatoId);
	}
}