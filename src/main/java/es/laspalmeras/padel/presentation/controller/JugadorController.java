package es.laspalmeras.padel.presentation.controller;

import java.util.List;

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
@Tag(name = "Jugador Management System", description = "Operations pertaining to players in Padel Club")
public class JugadorController {

	@Autowired
	private JugadorService jugadorService;

	@Operation(summary = "Create a new player")
	@PostMapping
	public Jugador createJugador(@RequestBody Jugador jugador) {
		return jugadorService.saveJugador(jugador);
	}

	@Operation(summary = "Delete a player by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
		jugadorService.deleteJugador(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Get all players")
	@GetMapping
	public List<Jugador> getAllJugadores() {
		return jugadorService.getAllJugadores();
	}

	@Operation(summary = "Get a player by ID")
	@GetMapping("/{id}")
	public ResponseEntity<Jugador> getJugadorById(@PathVariable Long id) {
		Jugador jugador = jugadorService.getJugadorById(id);
		return ResponseEntity.ok(jugador);
	}
	
	@Operation(summary = "Update a player by ID")
	@PutMapping("/{id}")
	public ResponseEntity<Jugador> updateJugador(@PathVariable Long id, @RequestBody Jugador jugadorDetails) {
		Jugador updatedJugador = jugadorService.updateJugador(id, jugadorDetails);
		return ResponseEntity.ok(updatedJugador);
	}
}