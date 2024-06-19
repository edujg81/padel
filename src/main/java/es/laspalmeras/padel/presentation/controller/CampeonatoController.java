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
import es.laspalmeras.padel.business.service.model.Campeonato;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	@Autowired
	private CampeonatoService campeonatoService;

	@PostMapping
	public Campeonato createCampeonato(@RequestBody Campeonato campeonato) {
		return campeonatoService.saveCampeonato(campeonato);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCampeonato(@PathVariable Long id) {
		campeonatoService.deleteCampeonato(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public List<Campeonato> getAllCampeonatos() {
		return campeonatoService.getAllCampeonatos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Campeonato> getCampeonatoById(@PathVariable Long id) {
		Campeonato campeonato = campeonatoService.getCampeonatoById(id);
		return ResponseEntity.ok(campeonato);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Campeonato> updateCampeonato(@PathVariable Long id,
			@RequestBody Campeonato campeonatoDetails) {
		Campeonato updatedCampeonato = campeonatoService.updateCampeonato(id, campeonatoDetails);
		return ResponseEntity.ok(updatedCampeonato);
	}
}
