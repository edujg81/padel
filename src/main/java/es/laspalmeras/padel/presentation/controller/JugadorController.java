package es.laspalmeras.padel.presentation.controller;

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
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import es.laspalmeras.padel.business.service.JugadorService;
import es.laspalmeras.padel.business.service.model.Jugador;

@RestController
@RequestMapping("/jugadores")
@Tag(name = "Gestión de Jugador", description = "Operaciones pertenecientes a jugadores registrados en Club de Padel")
public class JugadorController {

	@Autowired
	private JugadorService jugadorService;

	@Operation(summary = "Crear un nuevo jugador")
	@PostMapping
	public ResponseEntity<Long> createJugador(@RequestBody Jugador jugador) {
        Long id = jugadorService.create(jugador);
        return ResponseEntity.ok(id);
    }

	@Operation(summary = "Obtener un jugador por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Jugador> getJugadorById(@PathVariable Long id) {
		/*Jugador jugador = jugadorService.getJugadorById(id);
		return ResponseEntity.ok(jugador);*/
		Optional<Jugador> jugador = jugadorService.read(id);
        return jugador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Obtener todos los jugadores")
	@GetMapping
	public List<Jugador> getAllJugadores() {
		return jugadorService.getAllJugadores();
	}
	
	@Operation(summary = "Actualizar un jugador por ID")
	@PutMapping("/{id}")
	public ResponseEntity<Jugador> updateJugador(@PathVariable Long id, @RequestBody Jugador jugadorDetails) {
		Jugador updatedJugador = jugadorService.updateJugador(id, jugadorDetails);
		return ResponseEntity.ok(updatedJugador);
	}
	
	@Operation(summary = "Borrar un jugador por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
		jugadorService.deleteJugador(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Dar de baja un jugador por ID")
	@PostMapping("/baja/{id}")
    public ResponseEntity<Void> darDeBajaJugadorPorId(@PathVariable Long id) {
        jugadorService.darDeBajaJugadorPorId(id);
        return ResponseEntity.noContent().build();
    }

	@Operation(summary = "Dar de baja un jugador por DNI")
    @PostMapping("/baja/dni/{dni}")
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
}