package es.laspalmeras.padel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.dto.CampeonatoDTO;
import es.laspalmeras.padel.enums.EstadoCampeonato;
import es.laspalmeras.padel.exception.ResourceNotFoundException;
import es.laspalmeras.padel.mapper.CampeonatoMapper;
import es.laspalmeras.padel.model.Campeonato;
import es.laspalmeras.padel.model.Clasificacion;
import es.laspalmeras.padel.model.Inscripcion;
import es.laspalmeras.padel.model.Jornada;
import es.laspalmeras.padel.model.Jugador;
import es.laspalmeras.padel.model.Partido;
import es.laspalmeras.padel.repository.CampeonatoRepository;
import es.laspalmeras.padel.repository.ClasificacionRepository;
import es.laspalmeras.padel.repository.InscripcionRepository;
import es.laspalmeras.padel.service.CampeonatoService;

/**
 * Implementación del servicio de gestión de campeonatos.
 */
@Service
public class CampeonatoServiceImpl implements CampeonatoService{
	
    private final CampeonatoRepository campeonatoRepository;
    private final ClasificacionRepository clasificacionRepository;
    private final InscripcionRepository inscripcionRepository;
    private final CampeonatoMapper campeonatoMapper;
    
    public CampeonatoServiceImpl(CampeonatoRepository campeonatoRepository,
            ClasificacionRepository clasificacionRepository,
            InscripcionRepository inscripcionRepository,
            CampeonatoMapper campeonatoMapper) {
		this.campeonatoRepository = campeonatoRepository;
		this.clasificacionRepository = clasificacionRepository;
		this.inscripcionRepository = inscripcionRepository;
		this.campeonatoMapper = campeonatoMapper;
	}
	
    /**
     * Guarda un campeonato.
     * 
     * @param campeonato el campeonato a guardar.
     * @return el campeonato guardado.
     */
	@Override
	@Transactional
	public Campeonato saveCampeonato(Campeonato campeonato) {
		return campeonatoRepository.save(campeonato);
	}

	/**
     * Obtiene todos los campeonatos.
     *
     * @return una lista de objetos CampeonatoDTO
     */
	@Override
	@Transactional
	public List<CampeonatoDTO> findAllCampeonatos() {
		return campeonatoRepository.findAll().stream()
                .map(campeonatoMapper::toDto)
                .collect(Collectors.toList());
	}

	/**
     * Obtiene un campeonato por su ID.
     *
     * @param id el ID del campeonato
     * @return un Optional con el objeto CampeonatoDTO si se encuentra
     */
	@Override
	@Transactional
	public Optional<CampeonatoDTO> findCampeonatoById(Long id) {
		return campeonatoRepository.findById(id).map(campeonatoMapper::toDto);
	}

	/**
     * Elimina un campeonato por su ID.
     *
     * @param id el ID del campeonato
     */
	@Override
	@Transactional
	public void deleteCampeonato(Long id) {
		campeonatoRepository.deleteById(id);
	}

	/**
     * Crea un nuevo campeonato.
     *
     * @param campeonatoDTO el DTO del campeonato a crear
     * @return el objeto CampeonatoDTO creado
     * @throws IllegalStateException si ya existe un campeonato activo para la misma categoría y división en el mismo año
     */
	@Override
	@Transactional
	public CampeonatoDTO createCampeonato(CampeonatoDTO campeonatoDTO) {
		validarCampeonatoExistente(campeonatoDTO);
		
	    Campeonato campeonato = campeonatoMapper.toEntity(campeonatoDTO);
        saveCampeonato(campeonato);
        return campeonatoMapper.toDto(campeonato);
	}

	/**
     * Actualiza un campeonato existente.
     *
     * @param id el ID del campeonato a actualizar
     * @param campeonatoDetails los detalles actualizados del campeonato
     * @return el objeto CampeonatoDTO actualizado
     * @throws ResourceNotFoundException si el campeonato no se encuentra
     */
	@Override
	@Transactional
	public CampeonatoDTO updateCampeonato(Long id, CampeonatoDTO campeonatoDetails) {
		Campeonato campeonato = campeonatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato", "id", id));

        actualizarDatosCampeonato(campeonato, campeonatoDetails);
        saveCampeonato(campeonato);
        return campeonatoMapper.toDto(campeonato);
	}

	/**
     * Cambia el estado de un campeonato.
     * Si se intenta pasar a FINALIZADO, se verifica que todos los partidos (de cada jornada)
     * estén registrados. Además, si existen otros campeonatos de la misma categoría y año, se crean
     * automáticamente los campeonatos para el año siguiente aplicando ascensos y descensos.
     *
     * @param id el ID del campeonato
     * @param nuevoEstado el nuevo estado del campeonato
     * @throws ResourceNotFoundException si el campeonato no se encuentra
     */
	@Override
	@Transactional
	public void cambiarEstadoCampeonato(Long id, String nuevoEstado) {
		Campeonato campeonato = campeonatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato", "id", id));

		// Convertir el String a enum. Se asume que el String viene en mayúsculas o se ajusta con toUpperCase()
	    EstadoCampeonato estadoNuevo = EstadoCampeonato.valueOf(nuevoEstado.toUpperCase());
	    
		campeonato.setEstado(estadoNuevo);
        
		// Si el nuevo estado es EN_CURSO, generar la clasificación inicial
	    if (EstadoCampeonato.EN_CURSO.equals(estadoNuevo)) {
	        generarClasificacionInicial(campeonato);
	    }
	    
	    // Si se intenta pasar a FINALIZADO, se debe verificar que todos los partidos estén registrados.
	    if (EstadoCampeonato.FINALIZADO.equals(estadoNuevo)) {
	        // Iterar por cada jornada del campeonato.
	        if (campeonato.getJornadas() != null) {
	            for (Jornada jornada : campeonato.getJornadas()) {
	                // Suponemos que la entidad Jornada tiene un atributo List<Partido> partidos.
	                if (jornada.getPartidos() != null) {
	                    for (Partido partido : jornada.getPartidos()) {
	                        if (!Boolean.TRUE.equals(partido.getRegistrado())) {
	                            // Si se encuentra al menos un partido sin registrar, se lanza una excepción.
	                            throw new IllegalStateException("No es posible cambiar el estado a FINALIZADO: existen partidos sin registrar.");
	                        }
	                    }
	                }
	            }
	        }

	        // Si todos los partidos están registrados, se actualiza el estado.
	        campeonato.setEstado(EstadoCampeonato.FINALIZADO);
	        //campeonato.setActivo(false);
	        saveCampeonato(campeonato);

	        // Verificar la existencia de otros campeonatos de la misma categoría y año, pero de diferente división.
	        // Se obtienen todos los campeonatos activos del mismo año y categoría.
	        List<Campeonato> campeonatosMismaCategoria = campeonatoRepository.findAll().stream()
	                .filter(c -> c.getYear().equals(campeonato.getYear())
	                        && c.getCategoria().equals(campeonato.getCategoria())
	                        && c.getActivo())
	                .collect(Collectors.toList());

	        // Si solo existe el campeonato actual, no se realiza ninguna actuación adicional.
	        if (campeonatosMismaCategoria.size() > 1) {
	            // Se comprueba que todos los campeonatos de la misma categoría y año estén en estado FINALIZADO.
	            boolean todosFinalizados = campeonatosMismaCategoria.stream()
	                    .allMatch(c -> EstadoCampeonato.FINALIZADO.equals(c.getEstado()));
	            if (!todosFinalizados) {
	                throw new IllegalStateException("No es posible crear nuevos campeonatos: existen campeonatos no finalizados en otras divisiones.");
	            }

	            // Si todos están finalizados, se crean automáticamente los campeonatos para el año siguiente.
	            crearCampeonatosSiguientesConAscensosDescensos(campeonatosMismaCategoria, campeonato.getYear());
	            
	            // Marcar cada campeonato como inactivo
	            for (Campeonato c : campeonatosMismaCategoria) {
	                c.setActivo(false);
	            }
	            
	            // Guardar los cambios en lote
	            campeonatoRepository.saveAll(campeonatosMismaCategoria);
	        }
	    }
	    else {
	    	saveCampeonato(campeonato);
	    }
	}

	private void validarCampeonatoExistente(CampeonatoDTO campeonatoDTO) {
		List<Campeonato> campeonatosActivos = campeonatoRepository.findByYearAndCategoriaAndDivisionAndActivoTrue(
	            campeonatoDTO.getYear(), campeonatoDTO.getCategoria(), campeonatoDTO.getDivision());

	    if (!campeonatosActivos.isEmpty()) {
	        throw new IllegalStateException("Ya existe un campeonato activo para esta categoría y división en el mismo año.");
	    }
	}
	
	private void actualizarDatosCampeonato(Campeonato campeonato, CampeonatoDTO campeonatoDetails) {
        campeonato.setYear(campeonatoDetails.getYear());
        campeonato.setCategoria(campeonatoDetails.getCategoria());
        campeonato.setDivision(campeonatoDetails.getDivision());
        campeonato.setEstado(EstadoCampeonato.valueOf(campeonatoDetails.getEstado().toUpperCase()));
        campeonato.setActivo(campeonatoDetails.getActivo());
        campeonato.setPuntosPorVictoria(campeonatoDetails.getPuntosPorVictoria());
        campeonato.setPuntosPorDerrota(campeonatoDetails.getPuntosPorDerrota());
    }
	
	/**
     * Genera la clasificación inicial para un campeonato a partir de las inscripciones existentes.
     *
     * @param campeonato El campeonato para el que se generará la clasificación.
     */
    private void generarClasificacionInicial(Campeonato campeonato) {
        // Obtener inscripciones asociadas al campeonato
        List<Inscripcion> inscripciones = inscripcionRepository.findByCampeonatoId(campeonato.getId());

        if (inscripciones.isEmpty()) {
            throw new IllegalStateException("No hay jugadores inscritos en el campeonato.");
        }

        // Crear clasificaciones para cada inscripción y guardarlas en lote
        List<Clasificacion> clasificaciones = inscripciones.stream().map(inscripcion -> {
            Clasificacion clasificacion = new Clasificacion();
            clasificacion.setCampeonato(campeonato);
            clasificacion.setJugador(inscripcion.getJugador());
            clasificacion.setPosicion(0); // Se actualizará posteriormente
            clasificacion.setPuntos(0);
            clasificacion.setPartidosJugados(0);
            clasificacion.setPartidosGanados(0);
            clasificacion.setPartidosPerdidos(0);
            clasificacion.setSetsGanados(0);
            clasificacion.setSetsPerdidos(0);
            clasificacion.setJuegosGanados(0);
            clasificacion.setJuegosPerdidos(0);
            return clasificacion;
        }).collect(Collectors.toList());
        clasificacionRepository.saveAll(clasificaciones);
    }
    
    /**
     * Crea nuevos campeonatos para el año siguiente aplicando ascensos y descensos.
     * Se supone que los campeonatos actuales (de un mismo año y categoría) ya están finalizados.
     * Para cada división:
     *   - En la primera: se retienen todos los jugadores menos los 4 últimos; se agregan los 4 primeros de la siguiente división.
     *   - En divisiones intermedias: se retienen los jugadores sin los 4 primeros y sin los 4 últimos; se agregan los 4 últimos de la división anterior y los 4 primeros de la siguiente.
     *   - En la última: se retienen los jugadores sin los 4 primeros; se agregan los 4 últimos de la división anterior.
     * Luego, se crean los nuevos campeonatos para el año siguiente y se inscriben automáticamente los jugadores calculados.
     *
     * @param campeonatosActuales Lista de campeonatos actuales (finalizados) para una misma categoría
     * @param year      El año de los campeonatos actuales
     */
    @Transactional
    public void crearCampeonatosSiguientesConAscensosDescensos(List<Campeonato> campeonatosActuales, Integer year) {
        // 1. Agrupar campeonatos actuales por división
        // Se asume que en un mismo año y categoría hay un único campeonato por división.
        Map<Integer, Campeonato> campeonatosPorDivision = campeonatosActuales.stream()
                .collect(Collectors.toMap(Campeonato::getDivision, Function.identity()));

        // Obtener la lista de divisiones ordenadas ascendentemente (ej. 1, 2, 3, ...)
        List<Integer> divisiones = campeonatosPorDivision.keySet().stream().sorted().collect(Collectors.toList());
        int nextYear = year + 1;

        // 2. Para cada división, calcular la nueva lista de jugadores basada en ascensos y descensos.
        // Se crea un mapa: división -> lista de jugadores que formarán el nuevo campeonato
        Map<Integer, List<Jugador>> nuevasInscripciones = new HashMap<>();

        for (Integer division : divisiones) {
            Campeonato campActual = campeonatosPorDivision.get(division);
            // Obtener la clasificación completa ordenada para el campeonato actual
            List<Clasificacion> clasificaciones = clasificacionRepository.findClasificacionCompletaOrdenada(campActual.getId());
            int size = clasificaciones.size();
            List<Jugador> jugadoresNuevaDivision = new ArrayList<>();

            if (division.equals(divisiones.get(0))) { 
                // Primera división:
                // Se retienen todos los jugadores excepto los 4 últimos
                int limite = Math.max(0, size - 4);
                for (int i = 0; i < limite; i++) {
                    jugadoresNuevaDivision.add(clasificaciones.get(i).getJugador());
                }
                // Si existe una siguiente división, se agregan los 4 primeros de esa división (ascenso)
                if (divisiones.size() > 1) {
                    Campeonato campSiguiente = campeonatosPorDivision.get(divisiones.get(1));
                    List<Clasificacion> clasSiguiente = clasificacionRepository.findClasificacionCompletaOrdenada(campSiguiente.getId());
                    int numAscenso = Math.min(4, clasSiguiente.size());
                    for (int i = 0; i < numAscenso; i++) {
                        jugadoresNuevaDivision.add(clasSiguiente.get(i).getJugador());
                    }
                }
            } else if (division.equals(divisiones.get(divisiones.size() - 1))) { 
                // Última división:
                // Se retienen todos los jugadores excepto los 4 primeros (que ascienden)
                for (int i = 4; i < size; i++) {
                    jugadoresNuevaDivision.add(clasificaciones.get(i).getJugador());
                }
                // Se agregan los 4 últimos de la división anterior (descenso)
                Campeonato campAnterior = campeonatosPorDivision.get(divisiones.get(divisiones.indexOf(division) - 1));
                List<Clasificacion> clasAnterior = clasificacionRepository.findClasificacionCompletaOrdenada(campAnterior.getId());
                int start = Math.max(0, clasAnterior.size() - 4);
                for (int i = start; i < clasAnterior.size(); i++) {
                    jugadoresNuevaDivision.add(clasAnterior.get(i).getJugador());
                }
            } else {
                // Divisiones intermedias:
                // Se retienen los jugadores de la clasificación sin los 4 primeros (ascenso) y sin los 4 últimos (descenso)
                for (int i = 4; i < size - 4; i++) {
                    jugadoresNuevaDivision.add(clasificaciones.get(i).getJugador());
                }
                // Se agregan los 4 últimos de la división anterior (descenso)
                Campeonato campAnterior = campeonatosPorDivision.get(divisiones.get(divisiones.indexOf(division) - 1));
                List<Clasificacion> clasAnterior = clasificacionRepository.findClasificacionCompletaOrdenada(campAnterior.getId());
                int start = Math.max(0, clasAnterior.size() - 4);
                for (int i = start; i < clasAnterior.size(); i++) {
                    jugadoresNuevaDivision.add(clasAnterior.get(i).getJugador());
                }
                // Se agregan los 4 primeros de la siguiente división (ascenso)
                Campeonato campSiguiente = campeonatosPorDivision.get(divisiones.get(divisiones.indexOf(division) + 1));
                List<Clasificacion> clasSiguiente = clasificacionRepository.findClasificacionCompletaOrdenada(campSiguiente.getId());
                int numAscenso = Math.min(4, clasSiguiente.size());
                for (int i = 0; i < numAscenso; i++) {
                    jugadoresNuevaDivision.add(clasSiguiente.get(i).getJugador());
                }
            }
            nuevasInscripciones.put(division, jugadoresNuevaDivision);
        }

        // 3. Crear nuevos campeonatos para el año siguiente y registrar las inscripciones
        for (Integer division : divisiones) {
            Campeonato nuevoCampeonato = new Campeonato();
            nuevoCampeonato.setYear(nextYear);
            nuevoCampeonato.setCategoria(campeonatosPorDivision.get(division).getCategoria());
            nuevoCampeonato.setDivision(division);
            nuevoCampeonato.setEstado(EstadoCampeonato.SIN_INICIAR);
            nuevoCampeonato.setActivo(true);
            nuevoCampeonato.setPuntosPorVictoria(campeonatosPorDivision.get(division).getPuntosPorVictoria());
            nuevoCampeonato.setPuntosPorDerrota(campeonatosPorDivision.get(division).getPuntosPorDerrota());
            // Se guarda el nuevo campeonato
            nuevoCampeonato = campeonatoRepository.save(nuevoCampeonato);

            // Registrar las inscripciones: para cada jugador calculado, crear una inscripción si aún no existe
            List<Jugador> jugadoresParaInscribir = nuevasInscripciones.get(division);
            for (Jugador jugador : jugadoresParaInscribir) {
                if (!inscripcionRepository.existsByCampeonatoIdAndJugadorId(nuevoCampeonato.getId(), jugador.getId())) {
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setCampeonato(nuevoCampeonato);
                    inscripcion.setJugador(jugador);
                    inscripcionRepository.save(inscripcion);
                }
            }
        }
    }
}
