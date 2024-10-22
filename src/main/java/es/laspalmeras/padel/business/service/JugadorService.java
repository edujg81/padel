package es.laspalmeras.padel.business.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.business.service.dto.JugadorDTO;

public interface JugadorService {

	/**
	 * Devuelve el codigo que le ha otorgado el sistema.
	 *
	 * 
	 *
	 */
	Long create(JugadorDTO jugadorDTO);
	
	void deleteJugador(Long id);

	List<JugadorDTO> getAllJugadores();

	JugadorDTO getJugadorById(Long id);
	
	JugadorDTO getJugadorByDni(String dni);

	JugadorDTO updateJugador(Long id, JugadorDTO jugadorDetails);

	void darDeBajaJugadorPorId(Long id);

	void darDeBajaJugadorPorDni(String dni);

	void eliminarJugadoresBajaMasDeCincoAnios();

	JugadorDTO saveJugador(JugadorDTO jugadorDTO);

	Optional<JugadorDTO> read(Long id);

	List<JugadorDTO> getJugadoresDisponiblesParaCampeonato(Long campeonatoId);
}
