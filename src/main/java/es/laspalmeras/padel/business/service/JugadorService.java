package es.laspalmeras.padel.business.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.business.service.model.Jugador;

public interface JugadorService {

	Long create(Jugador jugador);
	
	Optional<Jugador> read(Long id);
	
	void deleteJugador(Long id);

	List<Jugador> getAllJugadores();

	Jugador getJugadorById(Long id);

	Jugador saveJugador(Jugador jugador);

	Jugador updateJugador(Long id, Jugador jugadorDetails);
}
