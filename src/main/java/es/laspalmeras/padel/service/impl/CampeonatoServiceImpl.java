package es.laspalmeras.padel.service.impl;

import java.util.List;
import java.util.Optional;
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
	
    private CampeonatoRepository campeonatoRepository;
    private ClasificacionRepository clasificacionRepository;
    private InscripcionRepository inscripcionRepository;
    private CampeonatoMapper campeonatoMapper;
    
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
	            int nextYear = campeonato.getYear() + 1;
	            for (Campeonato c : campeonatosMismaCategoria) {
	                Campeonato nuevoCampeonato = new Campeonato();
	                nuevoCampeonato.setYear(nextYear);
	                nuevoCampeonato.setCategoria(c.getCategoria());
	                nuevoCampeonato.setDivision(c.getDivision());
	                nuevoCampeonato.setEstado(EstadoCampeonato.SIN_INICIAR); // Estado inicial para el siguiente año
	                nuevoCampeonato.setActivo(true);
	                nuevoCampeonato.setPuntosPorVictoria(c.getPuntosPorVictoria());
	                nuevoCampeonato.setPuntosPorDerrota(c.getPuntosPorDerrota());
	                // Puedes copiar o configurar otros campos según la necesidad
	                campeonatoRepository.save(nuevoCampeonato);
	            }
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
     * Genera la clasificación inicial para un campeonato.
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
}
