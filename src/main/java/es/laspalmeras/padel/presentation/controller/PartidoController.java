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

import es.laspalmeras.padel.business.service.CampeonatoService;
import es.laspalmeras.padel.business.service.PartidoService;
import es.laspalmeras.padel.business.service.model.Partido;

@RestController
@RequestMapping("/partidos")
public class PartidoController {
	@Autowired
	private CampeonatoService campeonatoService;

	@Autowired
	private PartidoService partidoService;

	@PostMapping
	public Partido createPartido(@RequestBody Partido partido) {
		return partidoService.savePartido(partido);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePartido(@PathVariable Long id) {
		partidoService.deletePartido(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public List<Partido> getAllPartidos() {
		return partidoService.getAllPartidos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Partido> getPartidoById(@PathVariable Long id) {
		Partido partido = partidoService.getPartidoById(id);
		return ResponseEntity.ok(partido);
	}

	@PostMapping("/{partidoId}/registrar-resultado")
	public ResponseEntity<Partido> registrarResultado(@PathVariable Long partidoId, @RequestBody Partido resultado) {
		Partido partidoActualizado = campeonatoService.registrarResultadoPartido(partidoId, resultado);
		return ResponseEntity.ok(partidoActualizado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Partido> updatePartido(@PathVariable Long id, @RequestBody Partido partidoDetails) {
		Partido updatedPartido = partidoService.updatePartido(id, partidoDetails);
		return ResponseEntity.ok(updatedPartido);
	}
}