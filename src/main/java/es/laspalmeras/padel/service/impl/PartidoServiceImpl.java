package es.laspalmeras.padel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.dto.AusenciaDTO;
import es.laspalmeras.padel.dto.PartidoDTO;
import es.laspalmeras.padel.exception.ResourceNotFoundException;
import es.laspalmeras.padel.mapper.AusenciaMapper;
import es.laspalmeras.padel.mapper.PartidoMapper;
import es.laspalmeras.padel.model.Ausencia;
import es.laspalmeras.padel.model.Campeonato;
import es.laspalmeras.padel.model.Clasificacion;
import es.laspalmeras.padel.model.Inscripcion;
import es.laspalmeras.padel.model.Jornada;
import es.laspalmeras.padel.model.Jugador;
import es.laspalmeras.padel.model.Partido;
import es.laspalmeras.padel.repository.AusenciaRepository;
import es.laspalmeras.padel.repository.ClasificacionRepository;
import es.laspalmeras.padel.repository.InscripcionRepository;
import es.laspalmeras.padel.repository.JornadaRepository;
import es.laspalmeras.padel.repository.JugadorRepository;
import es.laspalmeras.padel.repository.PartidoRepository;
import es.laspalmeras.padel.service.PartidoService;
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
        partido.setFecha(partidoDetails.getFecha());
        //partido.setResultado(partidoDetails.getResultado());

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
        
        if (setsGanadosEquipo1 == setsGanadosEquipo2) {
        
	        if (partido.getJuegosGanadosEquipo1Set3() > partido.getJuegosGanadosEquipo2Set3()) {
	            setsGanadosEquipo1++;
	        } else {
	            setsGanadosEquipo2++;
	        }
        }

        partido.setSetsGanadosEquipo1(setsGanadosEquipo1);
        partido.setSetsGanadosEquipo2(setsGanadosEquipo2);
        
        partido.setResultado(setsGanadosEquipo1 + " - " + setsGanadosEquipo2);
        
        if (setsGanadosEquipo1 > setsGanadosEquipo2) {
            partido.setEquipoGanador("Equipo 1");
            
        } else {
            partido.setEquipoGanador("Equipo 2");
        }

        if (!partido.getRegistrado()) {
	        // Actualizar clasificación del campeonato
	        actualizarClasificacion(partido.getJornada().getCampeonato(), partido);
	        
	        // Procesar penalizaciones por ausencias
	        procesarAusencias(partido, partido.getJornada().getCampeonato());
	        
	        // Actualizar el punto para sustitutas en campeonatos femeninos
	        actualizarPuntoSustitutaFemenino(partido, partido.getJornada().getCampeonato());
	        
	        partido.setRegistrado(true);
        } else {
	    	System.out.println("Este partido ya se encuentra registrado");
	    }

        return partidoMapper.toDto(partidoRepository.save(partido));
    }
	
	@Transactional
	private void actualizarClasificacion(Campeonato campeonato, Partido partido) {
	    // Obtener las clasificaciones actuales del campeonato
	    List<Clasificacion> clasificaciones = clasificacionRepository.findByCampeonatoIdOrderByPosicionAsc(campeonato.getId());

	    for (Clasificacion clasificacion : clasificaciones) {
	        Jugador jugador = clasificacion.getJugador();

	        // Verifica si el jugador participó en el partido
	        boolean perteneceAEquipo1 = jugador.equals(partido.getEquipo1Jugador1()) || jugador.equals(partido.getEquipo1Jugador2());
	        boolean perteneceAEquipo2 = jugador.equals(partido.getEquipo2Jugador1()) || jugador.equals(partido.getEquipo2Jugador2());

	        if (perteneceAEquipo1 || perteneceAEquipo2) {
	            int puntos = clasificacion.getPuntos();

	            if (partido.getEquipoGanador().equals("Equipo 1") && perteneceAEquipo1) {
	                puntos += campeonato.getPuntosPorVictoria();
	                clasificacion.setPartidosGanados(clasificacion.getPartidosGanados() + 1);
	                actualizarSetsYJuegos(clasificacion, partido, true);
	            } else if (partido.getEquipoGanador().equals("Equipo 2") && perteneceAEquipo2) {
	                puntos += campeonato.getPuntosPorVictoria();
	                clasificacion.setPartidosGanados(clasificacion.getPartidosGanados() + 1);
	                actualizarSetsYJuegos(clasificacion, partido, false);
	            } else {
	                // Jugador participó en el partido pero su equipo perdió
	                puntos += campeonato.getPuntosPorDerrota();
	                clasificacion.setPartidosPerdidos(clasificacion.getPartidosPerdidos() + 1);

	                // Actualizar sets y juegos para el equipo perdedor
	                if (perteneceAEquipo1) {
	                    actualizarSetsYJuegos(clasificacion, partido, true);
	                } else {
	                    actualizarSetsYJuegos(clasificacion, partido, false);
	                }
	            }

	            clasificacion.setPuntos(puntos);
	            clasificacion.setPartidosJugados(clasificacion.getPartidosJugados() + 1);

	            // Guardar los cambios en la clasificación
	            clasificacionRepository.save(clasificacion);
	        }
	    }

	    // Actualizar las posiciones en la clasificación
	    actualizarPosiciones(clasificaciones);
	}

	/**
	 * Método auxiliar para actualizar sets y juegos ganados/perdidos.
	 *
	 * @param clasificacion La clasificación del jugador
	 * @param partido       El partido jugado
	 * @param esEquipo1     Indica si el jugador pertenece al equipo 1
	 */
	private void actualizarSetsYJuegos(Clasificacion clasificacion, Partido partido, boolean esEquipo1) {
	    if (esEquipo1) {
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
	    } else {
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
	    }
	}
	
	/**
	 * Actualiza las posiciones en la clasificación basándose en los criterios definidos:
	 * 1. Mayor puntuación
	 * 2. Más partidos ganados
	 * 3. Menos partidos perdidos
	 * 4. Mayor diferencia de sets ganados y perdidos
	 * 5. Mayor diferencia de juegos ganados y perdidos
	 *
	 * @param clasificaciones Lista de clasificaciones a ordenar y actualizar
	 */
	private void actualizarPosiciones(List<Clasificacion> clasificaciones) {
	    // Ordenar las clasificaciones según los criterios
	    clasificaciones.sort((c1, c2) -> {
	        // 1. Comparar por puntos (mayor es mejor)
	        int cmp = Integer.compare(c2.getPuntos(), c1.getPuntos());
	        if (cmp != 0) return cmp;

	        // 2. Comparar por partidos ganados (mayor es mejor)
	        cmp = Integer.compare(c2.getPartidosGanados(), c1.getPartidosGanados());
	        if (cmp != 0) return cmp;

	        // 3. Comparar por partidos perdidos (menor es mejor)
	        cmp = Integer.compare(c1.getPartidosPerdidos(), c2.getPartidosPerdidos());
	        if (cmp != 0) return cmp;

	        // 4. Comparar por diferencia de sets (mayor es mejor)
	        int diferenciaSetsC1 = c1.getSetsGanados() - c1.getSetsPerdidos();
	        int diferenciaSetsC2 = c2.getSetsGanados() - c2.getSetsPerdidos();
	        cmp = Integer.compare(diferenciaSetsC2, diferenciaSetsC1);
	        if (cmp != 0) return cmp;

	        // 5. Comparar por diferencia de juegos (mayor es mejor)
	        int diferenciaJuegosC1 = c1.getJuegosGanados() - c1.getJuegosPerdidos();
	        int diferenciaJuegosC2 = c2.getJuegosGanados() - c2.getJuegosPerdidos();
	        return Integer.compare(diferenciaJuegosC2, diferenciaJuegosC1);
	    });

	    // Actualizar las posiciones según el nuevo orden
	    for (int i = 0; i < clasificaciones.size(); i++) {
	        Clasificacion clasificacion = clasificaciones.get(i);
	        clasificacion.setPosicion(i + 1); // La posición comienza en 1
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
		Partido partido = partidoRepository.findById(partidoId)
		        .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado con id:" + partidoId));

	    // Validar que el partido no tenga ya 4 ausencias
	    if (partido.getAusencias() != null && partido.getAusencias().size() >= 4) {
	        throw new IllegalArgumentException("El partido ya tiene el máximo de 4 ausencias registradas.");
	    }
	    
	    // Verificar que el sustituto no sea ninguno de los jugadores titulares
	    if (sustitutoId.equals(partido.getEquipo1Jugador1().getId()) ||
	        sustitutoId.equals(partido.getEquipo1Jugador2().getId()) ||
	        sustitutoId.equals(partido.getEquipo2Jugador1().getId()) ||
	        sustitutoId.equals(partido.getEquipo2Jugador2().getId())) {
	        throw new IllegalArgumentException("El jugador sustituto no puede ser uno de los jugadores titulares del partido.");
	    }
	    
	    // Verificar que el sustituto esté inscrito en el campeonato del partido
	    Long campeonatoId = partido.getJornada().getCampeonato().getId();
	    boolean inscrito = inscripcionRepository.findByCampeonatoId(campeonatoId)
	            .stream()
	            .map(Inscripcion::getJugador)
	            .anyMatch(jugador -> jugador.getId().equals(sustitutoId));
	    if (!inscrito) {
	        throw new IllegalArgumentException("El jugador sustituto no está inscrito en el campeonato.");
	    }

	    // Registrar la ausencia	
		Ausencia ausencia = new Ausencia();
		ausencia.setPartido(partido);
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
	
	@Transactional
	private void procesarAusencias(Partido partido, Campeonato campeonato) {
	    if (partido.getAusencias() != null) {
	        for (Ausencia ausencia : partido.getAusencias()) {
	            Jugador ausente = ausencia.getAusente();
	            // Buscar la clasificación del jugador en este campeonato
	            Clasificacion clasificacion = clasificacionRepository
	                    .findByCampeonatoIdAndJugadorId(campeonato.getId(), ausente.getId())
	                    .orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador id: " + ausente.getId()));
	            
	            // Penalizar: asignar partido perdido con 2 sets en contra y 12 juegos en contra.
	            clasificacion.setPartidosPerdidos(clasificacion.getPartidosPerdidos() + 1);
	            clasificacion.setSetsPerdidos(clasificacion.getSetsPerdidos() + 2);
	            clasificacion.setJuegosPerdidos(clasificacion.getJuegosPerdidos() + 12);
	            clasificacion.setPartidosJugados(clasificacion.getPartidosJugados() + 1);
	            
	            clasificacionRepository.save(clasificacion);
	        }
	    }
	}
	
	private void actualizarPuntoSustitutaFemenino(Partido partido, Campeonato campeonato) {
	    if ("FEMENINO".equalsIgnoreCase(campeonato.getCategoria())) {
	        // Recorrer ausencias y actualizar al sustituto en caso de victoria
	        if (partido.getEquipoGanador().equals("Equipo 1") || partido.getEquipoGanador().equals("Equipo 2")) {
	            for (Ausencia ausencia : partido.getAusencias()) {
	                Jugador sustituto = ausencia.getSustituto();
	                // Se asume que el sustituto tiene su clasificación en el campeonato
	                Clasificacion clasificacion = clasificacionRepository
	                        .findByCampeonatoIdAndJugadorId(campeonato.getId(), sustituto.getId())
	                        .orElse(null);
	                // Solo se actualiza si existe la clasificación, y se ignoran el resto de estadísticas.
	                if (clasificacion != null) {
	                    clasificacion.setPuntos(clasificacion.getPuntos() + 1);
	                    clasificacionRepository.save(clasificacion);
	                }
	            }
	        }
	    }
	}
}
