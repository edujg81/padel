package es.laspalmeras.padel.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.PartidoService;
import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;
import es.laspalmeras.padel.integration.repository.AusenciaRepository;
import es.laspalmeras.padel.integration.repository.InscripcionRepository;
import es.laspalmeras.padel.integration.repository.JornadaRepository;
import es.laspalmeras.padel.integration.repository.JugadorRepository;
import es.laspalmeras.padel.integration.repository.PartidoRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PartidoServiceImpl implements PartidoService{

	@Autowired
    private PartidoRepository partidoRepository;
	
	@Autowired
    private AusenciaRepository ausenciaRepository;
	
	@Autowired
    private JugadorRepository jugadorRepository;
	
	@Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;
	
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
    @Transactional
    public List<Partido> createPartidosForJornada(Long jornadaId, List<Jugador> jugadores) {
        Jornada jornada = jornadaRepository.findById(jornadaId)
                .orElseThrow(() -> new ResourceNotFoundException("Jornada no encontrada con id: " + jornadaId));

        int numInscritos = jugadores.size();
        int numPartidos = numInscritos / 4;

        List<Partido> partidos = new ArrayList<>();
        for (int i = 0; i < numPartidos; i++) {
            Partido partido = new Partido();
            partido.setJornada(jornada);
            partido.setEquipo1Jugador1(jugadores.get(i * 4));
            partido.setEquipo1Jugador2(jugadores.get(i * 4 + 3));
            partido.setEquipo2Jugador1(jugadores.get(i * 4 + 1));
            partido.setEquipo2Jugador2(jugadores.get(i * 4 + 2));
            partido.setFecha(jornada.getFechaInicio());
            partidos.add(partido);
        }

        return partidoRepository.saveAll(partidos);
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
		return partidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id: " + id));
	}

	/*@Override
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
	}*/
	
	@Override
    @Transactional
    public Partido updatePartido(Long id, Partido partidoDetails) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id: " + id));

        partido.setJuegosGanadosEquipo1Set1(partidoDetails.getJuegosGanadosEquipo1Set1());
        partido.setJuegosGanadosEquipo1Set2(partidoDetails.getJuegosGanadosEquipo1Set2());
        partido.setJuegosGanadosEquipo1Set3(partidoDetails.getJuegosGanadosEquipo1Set3());
        partido.setJuegosGanadosEquipo2Set1(partidoDetails.getJuegosGanadosEquipo2Set1());
        partido.setJuegosGanadosEquipo2Set2(partidoDetails.getJuegosGanadosEquipo2Set2());
        partido.setJuegosGanadosEquipo2Set3(partidoDetails.getJuegosGanadosEquipo2Set3());
        /*partido.setAusenteJugador1(partidoDetails.getAusenteJugador1());
        partido.setAusenteJugador2(partidoDetails.getAusenteJugador2());
        partido.setAusenteJugador3(partidoDetails.getAusenteJugador3());
        partido.setAusenteJugador4(partidoDetails.getAusenteJugador4());
        partido.setSustitutoJugador1(partidoDetails.getSustitutoJugador1());
        partido.setSustitutoJugador2(partidoDetails.getSustitutoJugador2());
        partido.setSustitutoJugador3(partidoDetails.getSustitutoJugador3());
        partido.setSustitutoJugador4(partidoDetails.getSustitutoJugador4());*/
        partido.setPista(partidoDetails.getPista());
        partido.setResultado(partidoDetails.getResultado());

        // Determine ganador
        int setsGanadosEquipo1 = 0;
        int setsGanadosEquipo2 = 0;

        if (partido.getJuegosGanadosEquipo1Set1() > partido.getJuegosGanadosEquipo2Set1()) {
            setsGanadosEquipo1++;
        } else {
            setsGanadosEquipo2++;
        }
        if (partido.getJuegosGanadosEquipo1Set2() > partido.getJuegosGanadosEquipo2Set2()) {
            setsGanadosEquipo1++;
        } else {
            setsGanadosEquipo2++;
        }
        if (partido.getJuegosGanadosEquipo1Set3() > partido.getJuegosGanadosEquipo2Set3()) {
            setsGanadosEquipo1++;
        } else {
            setsGanadosEquipo2++;
        }

        if (setsGanadosEquipo1 > setsGanadosEquipo2) {
            partido.setEquipoGanador("Equipo 1");
        } else {
            partido.setEquipoGanador("Equipo 2");
        }

        partido.setRegistrado(true);

        // Actualizar clasificación del campeonato

        return partidoRepository.save(partido);
    }
	
    private void actualizarClasificacion(Campeonato campeonato, Partido partido) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByCampeonatoId(campeonato.getId());

        for (Inscripcion inscripcion : inscripciones) {
            Jugador jugador = inscripcion.getJugador();
            //int puntos = clasificacion.getPuntos(jugador);

            if (partido.getEquipoGanador().equals("Equipo 1") &&
                    (jugador.equals(partido.getEquipo1Jugador1()) || jugador.equals(partido.getEquipo1Jugador2()))) {
               // puntos += campeonato.getPuntosPorVictoria();
            } else if (partido.getEquipoGanador().equals("Equipo 2") &&
                    (jugador.equals(partido.getEquipo2Jugador1()) || jugador.equals(partido.getEquipo2Jugador2()))) {
              //  puntos += campeonato.getPuntosPorVictoria();
            } else {
              //  puntos += campeonato.getPuntosPorDerrota();
            }

           // clasificacion.setPuntos(puntos);
            inscripcionRepository.save(inscripcion);
        }
    }

	@Override
    public List<Partido> findPartidosByJornada(Long jornadaId) {
        return partidoRepository.findByJornadaId(jornadaId);
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

	@Override
	public List<Partido> findAllPartidos() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public Partido findPartidoById(Long id) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}
}
