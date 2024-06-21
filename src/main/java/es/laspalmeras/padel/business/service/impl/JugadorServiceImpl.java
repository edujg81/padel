package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.JugadorService;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.integration.repository.JugadorRepository;

@Service
public class JugadorServiceImpl implements JugadorService{

	@Autowired
    private JugadorRepository jugadorRepository;
	
	@Override
	public Long create(Jugador jugador) {
		Jugador savedJugador = jugadorRepository.save(jugador);
        return savedJugador.getId();
	}

	@Override
	public Optional<Jugador> read(Long id) {
		return jugadorRepository.findById(id);
	}

	@Override
	public void deleteJugador(Long id) {
		jugadorRepository.deleteById(id);
	}

	@Override
	public List<Jugador> getAllJugadores() {
		return jugadorRepository.findAll();
	}

	@Override
	public Jugador getJugadorById(Long id) {
		return jugadorRepository.findById(id).orElse(null);
	}

	@Override
	public Jugador saveJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	@Override
	public Jugador updateJugador(Long id, Jugador jugadorDetails) {
		return jugadorRepository.findById(id).map(jugador -> {
            jugador.setDni(jugadorDetails.getDni());
            jugador.setNombreCompleto(jugadorDetails.getNombreCompleto());
            jugador.setTelefono(jugadorDetails.getTelefono());
            jugador.setEmail(jugadorDetails.getEmail());
            jugador.setSexo(jugadorDetails.getSexo());
            jugador.setEstado(jugadorDetails.getEstado());
            jugador.setLesionado(jugadorDetails.getLesionado());
            return jugadorRepository.save(jugador);
        }).orElse(null);
	}

	@Override
	public Jugador getJugadorByDni(String dni) {
		return jugadorRepository.findByDni(dni).orElse(null);
	}

}
