package es.laspalmeras.padel.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.integration.repository.JugadorRepository;

@Service
public class JugadorService {

	@Autowired
	private JugadorRepository jugadorRepository;

	public void deleteJugador(Long id) {
		jugadorRepository.deleteById(id);
	}

	public List<Jugador> getAllJugadores() {
		return jugadorRepository.findAll();
	}

	public Jugador getJugadorById(Long id) {
		return jugadorRepository.findById(id).orElse(null);
	}

	public Jugador saveJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	public Jugador updateJugador(Long id, Jugador jugadorDetails) {
		Jugador jugador = jugadorRepository.findById(id).orElse(null);
		if (jugador != null) {
			jugador.setDni(jugadorDetails.getDni());
			jugador.setNombreCompleto(jugadorDetails.getNombreCompleto());
			jugador.setTelefono(jugadorDetails.getTelefono());
			jugador.setEmail(jugadorDetails.getEmail());
			jugador.setSexo(jugadorDetails.getSexo());
			jugador.setEstado(jugadorDetails.isEstado());
			jugador.setLesionado(jugadorDetails.isLesionado());
			return jugadorRepository.save(jugador);
		}
		return null;
	}
}
