package es.laspalmeras.padel.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Clasificacion;
import es.laspalmeras.padel.business.service.model.Partido;
import es.laspalmeras.padel.integration.repository.CampeonatoRepository;
import es.laspalmeras.padel.integration.repository.ClasificacionRepository;
import es.laspalmeras.padel.integration.repository.PartidoRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

@Service
public class CampeonatoService {
	private final CampeonatoRepository campeonatoRepository;

	@Autowired
	private PartidoRepository partidoRepository;

	@Autowired
	private ClasificacionRepository clasificacionRepository;

	public CampeonatoService(CampeonatoRepository campeonatoRepository) {
		this.campeonatoRepository = campeonatoRepository;
	}

	private void actualizarClasificacion(Partido partido) {
		List<Clasificacion> clasificaciones = clasificacionRepository
				.findByCampeonatoOrderByPosicionAsc(partido.getJornada().getCampeonato());

		// Lógica para actualizar la clasificación basándose en los resultados del
		// partido
		// Equipo 1
		Clasificacion clasificacionEquipo1Jugador1 = clasificaciones.stream()
				.filter(c -> c.getJugador().equals(partido.getEquipo1Jugador1())).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

		Clasificacion clasificacionEquipo1Jugador2 = clasificaciones.stream()
				.filter(c -> c.getJugador().equals(partido.getEquipo1Jugador2())).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

		// Equipo 2
		Clasificacion clasificacionEquipo2Jugador1 = clasificaciones.stream()
				.filter(c -> c.getJugador().equals(partido.getEquipo2Jugador1())).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

		Clasificacion clasificacionEquipo2Jugador2 = clasificaciones.stream()
				.filter(c -> c.getJugador().equals(partido.getEquipo2Jugador2())).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

		// Actualizar la lógica de clasificación
		actualizarEstadisticas(clasificacionEquipo1Jugador1, partido, true);
		actualizarEstadisticas(clasificacionEquipo1Jugador2, partido, true);
		actualizarEstadisticas(clasificacionEquipo2Jugador1, partido, false);
		actualizarEstadisticas(clasificacionEquipo2Jugador2, partido, false);

		clasificacionRepository.save(clasificacionEquipo1Jugador1);
		clasificacionRepository.save(clasificacionEquipo1Jugador2);
		clasificacionRepository.save(clasificacionEquipo2Jugador1);
		clasificacionRepository.save(clasificacionEquipo2Jugador2);
	}

	private void actualizarEstadisticas(Clasificacion clasificacion, Partido partido, boolean esGanador) {
		clasificacion.setPartidosJugados(clasificacion.getPartidosJugados() + 1);
		if (esGanador) {
			clasificacion.setPartidosGanados(clasificacion.getPartidosGanados() + 1);
			clasificacion.setPuntos(clasificacion.getPuntos() + 2);
		} else {
			clasificacion.setPartidosPerdidos(clasificacion.getPartidosPerdidos() + 1);
		}

		clasificacion.setSetsGanados(clasificacion.getSetsGanados()
				+ (esGanador ? partido.getSetsGanadosEquipo1() : partido.getSetsGanadosEquipo2()));
		clasificacion.setSetsPerdidos(clasificacion.getSetsPerdidos()
				+ (esGanador ? partido.getSetsPerdidosEquipo1() : partido.getSetsPerdidosEquipo2()));
		clasificacion.setJuegosGanados(clasificacion.getJuegosGanados()
				+ (esGanador ? partido.getJuegosGanadosEquipo1() : partido.getJuegosGanadosEquipo2()));
		clasificacion.setJuegosPerdidos(clasificacion.getJuegosPerdidos()
				+ (esGanador ? partido.getJuegosPerdidosEquipo1() : partido.getJuegosPerdidosEquipo2()));

		// Actualizar diferencia de sets y juegos
		clasificacion.setDiferenciaSets(clasificacion.getSetsGanados() - clasificacion.getSetsPerdidos());
		clasificacion.setDiferenciaJuegos(clasificacion.getJuegosGanados() - clasificacion.getJuegosPerdidos());
	}

	public void deleteCampeonato(Long id) {
		campeonatoRepository.deleteById(id);
	}

	public List<Campeonato> getAllCampeonatos() {
		return campeonatoRepository.findAll();
	}

	public Campeonato getCampeonatoById(Long campeonatoId) {
		return campeonatoRepository.findById(campeonatoId)
				.orElseThrow(() -> new ResourceNotFoundException("Campeonato not found with id " + campeonatoId));
	}

	public Partido registrarResultadoPartido(Long partidoId, Partido resultado) {
		Partido partido = partidoRepository.findById(partidoId)
				.orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));

		// Actualizar el partido con los resultados y datos proporcionados
		partido.setResultado(resultado.getResultado());
		partido.setPista(resultado.getPista());
		partido.setFecha(resultado.getFecha());
		partido.setAusente(resultado.getAusente());
		partido.setLesionado(resultado.getLesionado());
		partido.setSustituto(resultado.getSustituto());
		partido.setJuegosGanadosEquipo1(resultado.getJuegosGanadosEquipo1());
		partido.setJuegosPerdidosEquipo1(resultado.getJuegosPerdidosEquipo1());
		partido.setSetsGanadosEquipo1(resultado.getSetsGanadosEquipo1());
		partido.setSetsPerdidosEquipo1(resultado.getSetsPerdidosEquipo1());
		partido.setJuegosGanadosEquipo2(resultado.getJuegosGanadosEquipo2());
		partido.setJuegosPerdidosEquipo2(resultado.getJuegosPerdidosEquipo2());
		partido.setSetsGanadosEquipo2(resultado.getSetsGanadosEquipo2());
		partido.setSetsPerdidosEquipo2(resultado.getSetsPerdidosEquipo2());

		actualizarClasificacion(partido);
		return partidoRepository.save(partido);
	}

	public Campeonato saveCampeonato(Campeonato campeonato) {
		return campeonatoRepository.save(campeonato);
	}

	public Campeonato updateCampeonato(Long id, Campeonato campeonatoDetails) {
		Campeonato campeonato = campeonatoRepository.findById(id).orElse(null);
		if (campeonato != null) {
			campeonato.setCategoria(campeonatoDetails.getCategoria());
			campeonato.setDivision(campeonatoDetails.getDivision());
			campeonato.setActivo(campeonatoDetails.isActivo());
			return campeonatoRepository.save(campeonato);
		}
		return null;
	}
}