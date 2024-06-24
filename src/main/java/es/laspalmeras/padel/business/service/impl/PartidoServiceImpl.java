package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.PartidoService;
import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Partido;
import es.laspalmeras.padel.integration.repository.PartidoRepository;
import es.laspalmeras.padel.integration.repository.AusenciaRepository;
import es.laspalmeras.padel.integration.repository.JugadorRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

@Service
public class PartidoServiceImpl implements PartidoService{

	@Autowired
    private PartidoRepository partidoRepository;
	
	@Autowired
    private AusenciaRepository ausenciaRepository;
	
	@Autowired
    private JugadorRepository jugadorRepository;
	
	@Override
	public Optional<Partido> read(Long id) {
		 return partidoRepository.findById(id);
	}
	
	@Override
	public Partido savePartido(Partido partido) {
		return partidoRepository.save(partido);
	}

	@Override
	public List<Partido> getAllPartidos() {
		return partidoRepository.findAll();
	}

	@Override
	public List<Partido> createPartidosForJornada(Long jornadaId, List<Long> jugadorIds) {
		// Lógica para crear los partidos de una jornada con los jugadores dados
        // Dividir los jugadores en equipos y asignar partidos
        return null; // Implementar lógica
	}

	@Override
	public Long create(Partido partido) {
		return partidoRepository.save(partido).getId();
	}

	@Override
	public void deletePartido(Long id) {
		partidoRepository.deleteById(id);
	}

	@Override
	public Partido getPartidoById(Long id) {
		return partidoRepository.findById(id).orElse(null);
	}

	@Override
	public Partido updatePartido(Long id, Partido partidoDetails) {
		Partido partido = partidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));
        partido.setJornada(partidoDetails.getJornada());
        partido.setEquipo1Jugador1(partidoDetails.getEquipo1Jugador1());
        partido.setEquipo1Jugador2(partidoDetails.getEquipo1Jugador2());
        partido.setEquipo2Jugador1(partidoDetails.getEquipo2Jugador1());
        partido.setEquipo2Jugador2(partidoDetails.getEquipo2Jugador2());
        partido.setResultado(partidoDetails.getResultado());
        partido.setPista(partidoDetails.getPista());
        partido.setFecha(partidoDetails.getFecha());
        partido.setJuegosGanadosEquipo1Set1(partidoDetails.getJuegosGanadosEquipo1Set1());
        partido.setJuegosGanadosEquipo2Set1(partidoDetails.getJuegosGanadosEquipo2Set1());
        partido.setJuegosGanadosEquipo1Set2(partidoDetails.getJuegosGanadosEquipo1Set2());
        partido.setJuegosGanadosEquipo2Set2(partidoDetails.getJuegosGanadosEquipo2Set2());
        partido.setJuegosGanadosEquipo1Set3(partidoDetails.getJuegosGanadosEquipo1Set3());
        partido.setJuegosGanadosEquipo2Set3(partidoDetails.getJuegosGanadosEquipo2Set3());
        partido.setEquipoGanador(partidoDetails.getEquipoGanador());
        return partidoRepository.save(partido);
	}

	@Override
	public void registrarAusencia(Long partidoId, Long ausenteId, Long sustitutoId) {
		Ausencia ausencia = new Ausencia();
        ausencia.setPartido(partidoRepository.findById(partidoId).orElseThrow(() -> new ResourceNotFoundException("Partido", "id", partidoId)));
        ausencia.setAusente(jugadorRepository.findById(ausenteId).orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", ausenteId)));
        ausencia.setSustituto(jugadorRepository.findById(sustitutoId).orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", sustitutoId)));
        ausenciaRepository.save(ausencia);
	}

	@Override
	public List<Ausencia> getAusenciasByPartidoId(Long partidoId) {
		return ausenciaRepository.findByPartidoId(partidoId);
	}
}
