package es.laspalmeras.padel.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.dto.JugadorDTO;

public interface JugadorService {

	Long create(JugadorDTO jugadorDTO);
	
	void deleteJugador(Long id);

	List<JugadorDTO> getAllJugadores();

	Optional<JugadorDTO> getJugadorById(Long id);
	
	JugadorDTO getJugadorByDni(String dni);

	JugadorDTO updateJugador(Long id, JugadorDTO jugadorDetails);
	
	void darDeBajaJugadorPorId(Long id);

	void darDeBajaJugadorPorDni(String dni);

	void eliminarJugadoresBajaMasDeCincoAnios();

	JugadorDTO saveJugador(JugadorDTO jugadorDTO);

	List<JugadorDTO> getJugadoresDisponiblesParaCampeonato(Long campeonatoId);
}
