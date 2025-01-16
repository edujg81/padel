package es.laspalmeras.padel.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.PartidoService;
import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.mapper.AusenciaMapper;
import es.laspalmeras.padel.business.service.mapper.PartidoMapper;
import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Clasificacion;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;
import es.laspalmeras.padel.integration.repository.AusenciaRepository;
import es.laspalmeras.padel.integration.repository.ClasificacionRepository;
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

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Autowired
    private PartidoMapper partidoMapper;
	
	@Override
	public Optional<PartidoDTO> read(Long id) {
		return partidoRepository.findById(id).map(partidoMapper::toDto);
	}
	
	@Override
	@Transactional
	public PartidoDTO savePartido(PartidoDTO partidoDTO) {
		Partido partido = partidoMapper.toEntity(partidoDTO);
        return partidoMapper.toDto(partidoRepository.save(partido));
	}

	@Override
	public List<PartidoDTO> getAllPartidos() {
		return partidoRepository.findAll().stream()
				.map(partidoMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
    @Transactional
    public List<PartidoDTO> createPartidosForJornada(Long jornadaId) {
        Jornada jornada = jornadaRepository.findById(jornadaId)
                .orElseThrow(() -> new ResourceNotFoundException("Jornada no encontrada con id: " + jornadaId));
        
        List<Jugador> jugadores = inscripcionRepository.findByCampeonatoId(jornada.getCampeonato().getId())
                .stream().map(Inscripcion::getJugador).collect(Collectors.toList());
        
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

        return partidoRepository.saveAll(partidos).stream()
        		.map(partidoMapper::toDto)
        		.collect(Collectors.toList());
    }

	@Override
	@Transactional
	public void deletePartido(Long id) {
		partidoRepository.deleteById(id);
	}

	@Override
	public PartidoDTO getPartidoById(Long id) {
		return partidoRepository.findById(id)
				.map(partidoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id: " + id));
	}

	@Override
    @Transactional
    public PartidoDTO updatePartido(Long id, PartidoDTO partidoDetails) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id: " + id));
        
        // Actualización de los campos
        partido.setJuegosGanadosEquipo1Set1(partidoDetails.getJuegosGanadosEquipo1Set1());
        partido.setJuegosGanadosEquipo1Set2(partidoDetails.getJuegosGanadosEquipo1Set2());
        partido.setJuegosGanadosEquipo1Set3(partidoDetails.getJuegosGanadosEquipo1Set3());
        partido.setJuegosGanadosEquipo2Set1(partidoDetails.getJuegosGanadosEquipo2Set1());
        partido.setJuegosGanadosEquipo2Set2(partidoDetails.getJuegosGanadosEquipo2Set2());
        partido.setJuegosGanadosEquipo2Set3(partidoDetails.getJuegosGanadosEquipo2Set3());
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
        
        if (partido.getJuegosGanadosEquipo1Set3() != null && partido.getJuegosGanadosEquipo2Set3() != null) {
	        if (partido.getJuegosGanadosEquipo1Set3() > partido.getJuegosGanadosEquipo2Set3()) {
	            setsGanadosEquipo1++;
	        } else {
	            setsGanadosEquipo2++;
	        }
        }

        partido.setSetsGanadosEquipo1(setsGanadosEquipo1);
        partido.setSetsGanadosEquipo2(setsGanadosEquipo2);
        
        if (setsGanadosEquipo1 > setsGanadosEquipo2) {
            partido.setEquipoGanador("Equipo 1");
            
        } else {
            partido.setEquipoGanador("Equipo 2");
        }

        partido.setRegistrado(true);

        // Actualizar clasificación del campeonato
        actualizarClasificacion(partido.getJornada().getCampeonato(), partido);

        return partidoMapper.toDto(partidoRepository.save(partido));
    }
	
	@Transactional
    private void actualizarClasificacion(Campeonato campeonato, Partido partido) {
    	List<Clasificacion> clasificaciones = clasificacionRepository.findByCampeonatoIdOrderByPuntosDesc(campeonato.getId());

    	for (Clasificacion clasificacion : clasificaciones) {
            Jugador jugador = clasificacion.getJugador();
            int puntos = clasificacion.getPuntos();
            
            if (partido.getEquipoGanador().equals("Equipo 1") &&
                    (jugador.equals(partido.getEquipo1Jugador1()) || jugador.equals(partido.getEquipo1Jugador2()))) {
                puntos += campeonato.getPuntosPorVictoria();
                clasificacion.setPartidosGanados(clasificacion.getPartidosGanados() + 1);
                clasificacion.setSetsGanados(clasificacion.getSetsGanados() + partido.getSetsGanadosEquipo1());
                clasificacion.setSetsPerdidos(clasificacion.getSetsPerdidos() + partido.getSetsGanadosEquipo2());
                clasificacion.setJuegosGanados(clasificacion.getJuegosGanados() 
                		+ partido.getJuegosGanadosEquipo1Set1() 
                		+ partido.getJuegosGanadosEquipo1Set2() 
                		+ partido.getJuegosGanadosEquipo1Set3());
                clasificacion.setJuegosPerdidos(clasificacion.getJuegosPerdidos() 
                		+ partido.getJuegosGanadosEquipo2Set1() 
                		+ partido.getJuegosGanadosEquipo2Set2() 
                		+ partido.getJuegosGanadosEquipo2Set3());
            } else if (partido.getEquipoGanador().equals("Equipo 2") &&
                    (jugador.equals(partido.getEquipo2Jugador1()) || jugador.equals(partido.getEquipo2Jugador2()))) {
                puntos += campeonato.getPuntosPorVictoria();
                clasificacion.setPartidosGanados(clasificacion.getPartidosGanados() + 1);
                clasificacion.setSetsGanados(clasificacion.getSetsGanados() + partido.getSetsGanadosEquipo2());
                clasificacion.setSetsPerdidos(clasificacion.getSetsPerdidos() + partido.getSetsGanadosEquipo1());
                clasificacion.setJuegosGanados(clasificacion.getJuegosGanados() 
                		+ partido.getJuegosGanadosEquipo2Set1() 
                		+ partido.getJuegosGanadosEquipo2Set2() 
                		+ partido.getJuegosGanadosEquipo2Set3());
                clasificacion.setJuegosPerdidos(clasificacion.getJuegosPerdidos() 
                		+ partido.getJuegosGanadosEquipo1Set1() 
                		+ partido.getJuegosGanadosEquipo1Set2() 
                		+ partido.getJuegosGanadosEquipo1Set3());
            } else {
                puntos += campeonato.getPuntosPorDerrota();
                clasificacion.setPartidosPerdidos(clasificacion.getPartidosPerdidos() + 1);
            }

            clasificacion.setPuntos(puntos);
            clasificacion.setPartidosJugados(clasificacion.getPartidosJugados() + 1);
            
            clasificacionRepository.save(clasificacion);
        }
    }

	@Override
    public List<PartidoDTO> getPartidosByJornada(Long jornadaId) {
		return partidoRepository.findByJornadaId(jornadaId).stream()
				.map(partidoMapper::toDto)
				.collect(Collectors.toList());
    }
	
	@Override
	@Transactional
	public void registrarAusencia(Long partidoId, Long ausenteId, Long sustitutoId) {
		Ausencia ausencia = new Ausencia();
        ausencia.setPartido(partidoRepository.findById(partidoId)
        		.orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id:" + partidoId)));
        ausencia.setAusente(jugadorRepository.findById(ausenteId)
        		.orElseThrow(() -> new ResourceNotFoundException("Jugador ausente no encontrado con id:" + ausenteId)));
        ausencia.setSustituto(jugadorRepository.findById(sustitutoId)
        		.orElseThrow(() -> new ResourceNotFoundException("Jugador sustituto no encontrado con id:" + sustitutoId)));
        ausenciaRepository.save(ausencia);
	}

	@Override
	public List<AusenciaDTO> getAusenciasByPartidoId(Long partidoId) {
		return ausenciaRepository.findByPartidoId(partidoId).stream()
				.map(AusenciaMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
}
