package es.laspalmeras.padel.jornada;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.campeonato.Campeonato;
import es.laspalmeras.padel.campeonato.CampeonatoRepository;
import es.laspalmeras.padel.clasificacion.Clasificacion;
import es.laspalmeras.padel.clasificacion.ClasificacionRepository;
import es.laspalmeras.padel.exception.ResourceNotFoundException;
import es.laspalmeras.padel.inscripcion.Inscripcion;
import es.laspalmeras.padel.inscripcion.InscripcionRepository;
import es.laspalmeras.padel.jugador.Jugador;
import es.laspalmeras.padel.partido.Partido;
import es.laspalmeras.padel.partido.PartidoRepository;
import jakarta.transaction.Transactional;

/**
 * Implementación del servicio de gestión de jornadas.
 */
@Service
public class JornadaServiceImpl implements JornadaService {

	@Autowired
	private JornadaRepository jornadaRepository;
	
	@Autowired
	private CampeonatoRepository campeonatoRepository;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private InscripcionRepository inscripcionRepository;
	
	@Autowired
	private ClasificacionRepository clasificacionRepository;
	
	@Autowired
	private JornadaMapper jornadaMapper;
	
	/**
     * Constructor de la clase JornadaServiceImpl.
     *
     * @param jornadaRepository el repositorio de jornadas
     * @param jornadaMapper el mapper de jornadas
     */
    public JornadaServiceImpl(JornadaRepository jornadaRepository, JornadaMapper jornadaMapper) {
        this.jornadaRepository = jornadaRepository;
        this.jornadaMapper = jornadaMapper;
    }
	
    /**
     * Obtiene todas las jornadas.
     *
     * @return una lista de objetos JornadaDTO
     */
	@Override
	@Transactional
    public List<JornadaDTO> findAllJornadas() {
    	return jornadaRepository.findAll().stream()
    			.map(jornadaMapper::toDto)
    			.collect(Collectors.toList());
    }
    
	/**
     * Obtiene una jornada por su ID.
     *
     * @param id el ID de la jornada
     * @return un Optional con el objeto JornadaDTO si se encuentra
     */
    @Override
    @Transactional
    public Optional<JornadaDTO> findJornadaById(Long id) {
        return jornadaRepository.findById(id).map(jornadaMapper::toDto);
    }
    
    /**
     * Obtiene las jornadas de un campeonato específico.
     *
     * @param campeonatoId el ID del campeonato
     * @return una lista de objetos JornadaDTO
     */
    @Override
    @Transactional
    public List<JornadaDTO> findJornadasByCampeonato(Long campeonatoId) {
        return jornadaRepository.findByCampeonatoId(campeonatoId).stream()
        		.map(jornadaMapper::toDto)
        		.collect(Collectors.toList());
    }
    
    /**
     * Elimina una jornada por su ID.
     *
     * @param id el ID de la jornada
     */
    @Override
    @Transactional
    public void deleteJornada(Long id) {
        jornadaRepository.deleteById(id);
    }
    
    /**
     * Crea una nueva jornada.
     *
     * @param campeonatoId el ID del campeonato
     * @param fechaInicio la fecha de inicio de la jornada
     * @return el objeto JornadaDTO creado
     */
    @Override
    @Transactional
    public JornadaDTO createJornada(Long campeonatoId, LocalDate fechaInicio) {
        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con id: " + campeonatoId));

        // Log de validación del estado
        if (!"En curso".equals(campeonato.getEstado())) {
            String errorMsg = "El campeonato con id " + campeonatoId + " no está 'En curso'. Estado actual: " + campeonato.getEstado();
            System.err.println(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        // Log de validación de duplicados
        if (jornadaRepository.existsByCampeonatoIdAndFechaInicio(campeonatoId, fechaInicio)) {
            String errorMsg = "Ya existe una jornada con la fecha " + fechaInicio + " para el campeonato con id: " + campeonatoId;
            System.err.println(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        // Log de inscripciones
        List<Inscripcion> inscripciones = inscripcionRepository.findByCampeonatoId(campeonatoId);
        System.out.println("Inscripciones encontradas: " + inscripciones.size());
        if (inscripciones.isEmpty()) {
            throw new IllegalArgumentException("No hay jugadores inscritos en el campeonato con id: " + campeonatoId);
        }
        
        // Obtener el número de jornada que se está generando
        long numeroJornada = jornadaRepository.countByCampeonatoId(campeonatoId) + 1;

        // Determinar los jugadores a utilizar para la generación de partidos
        List<Jugador> jugadores = obtenerJugadoresParaJornada(campeonatoId, numeroJornada);

        if (jugadores.size() < 4 || jugadores.size() % 4 != 0) {
            throw new IllegalArgumentException("El número de jugadores no es suficiente para formar equipos. Se necesitan múltiplos de 4.");
        }
        
        //int numInscritos = inscripciones.size();
        //int numPartidos = numInscritos / 4;
        
        // Crear partidos a partir de los jugadores
        int numPartidos = jugadores.size() / 4;
        List<Partido> partidos = generarPartidos(jugadores, numPartidos);

        // Generar partidos
        //List<Partido> partidos = generarPartidos(inscripciones, numPartidos);

        Jornada nuevaJornada = new Jornada();
        nuevaJornada.setCampeonato(campeonato);
        nuevaJornada.setFechaInicio(fechaInicio);
        nuevaJornada.setNumero((int) numeroJornada);
        
        Jornada savedJornada = jornadaRepository.save(nuevaJornada);

        // Asociar y guardar los partidos de la jornada
        partidos.forEach(partido -> {
            partido.setJornada(savedJornada);
            partidoRepository.save(partido);
        });

        System.out.println("Jornada creada con éxito: " + savedJornada.getId());
        return jornadaMapper.toDto(savedJornada);
    }
    
    /**
     * Obtiene la lista de jugadores para la jornada, ordenada según el criterio definido:
     * - Para la jornada 1, se usa el orden de inscripción.
     * - Para jornadas sucesivas, se usa el orden de clasificación.
     *
     * @param campeonatoId El ID del campeonato
     * @param numeroJornada El número de la jornada que se está generando
     * @return Lista de jugadores ordenada
     */
    private List<Jugador> obtenerJugadoresParaJornada(Long campeonatoId, long numeroJornada) {
        if (numeroJornada == 1) {
            // Jornada 1: Ordenar jugadores según el orden de inscripción
            return inscripcionRepository.findByCampeonatoId(campeonatoId).stream()
                    .map(Inscripcion::getJugador)
                    .collect(Collectors.toList());
        } else {
            // Jornadas sucesivas: Ordenar jugadores según la clasificación
            return clasificacionRepository.findByCampeonatoIdOrderByPosicionAsc(campeonatoId).stream()
                    .map(Clasificacion::getJugador)
                    .collect(Collectors.toList());
        }
    }
    
    /**
     * Genera los partidos para una jornada.
     *
     * @param inscripciones la lista de inscripciones
     * @param numPartidos el número de partidos a generar
     * @return una lista de objetos Partido
     */
    @Transactional
    private List<Partido> generarPartidos(List<Jugador> jugadores, int numPartidos) {
//        List<Jugador> jugadores = inscripciones.stream()
//                .map(Inscripcion::getJugador)
//                .collect(Collectors.toList());
//
//        if (jugadores.size() < 4 || jugadores.size() % 4 != 0) {
//            throw new IllegalArgumentException("El número de jugadores inscritos no es suficiente para formar equipos. Se necesitan múltiplos de 4.");
//        }
        
        return jugadores.stream()
        		.collect(Collectors.groupingBy(jugador -> jugadores.indexOf(jugador) / 4))
        		.values().stream()
        		.map(jugadoresGrupo -> {
        			Partido partido = new Partido();
                    partido.setEquipo1Jugador1(jugadoresGrupo.get(0));
                    partido.setEquipo1Jugador2(jugadoresGrupo.get(3));
                    partido.setEquipo2Jugador1(jugadoresGrupo.get(1));
                    partido.setEquipo2Jugador2(jugadoresGrupo.get(2));
                    return partido;
        		}).collect(Collectors.toList());
    }
}