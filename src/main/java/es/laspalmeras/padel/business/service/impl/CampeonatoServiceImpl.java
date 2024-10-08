package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.business.service.CampeonatoService;
import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.mapper.CampeonatoMapper;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.integration.repository.CampeonatoRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

/**
 * Implementación del servicio de gestión de campeonatos.
 */
@Service
public class CampeonatoServiceImpl implements CampeonatoService{
	
	@Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private CampeonatoMapper campeonatoMapper;
	
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

        campeonato.setEstado(nuevoEstado);
        saveCampeonato(campeonato);
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
        campeonato.setEstado(campeonatoDetails.getEstado());
        campeonato.setActivo(campeonatoDetails.getActivo());
        campeonato.setPuntosPorVictoria(campeonatoDetails.getPuntosPorVictoria());
        campeonato.setPuntosPorDerrota(campeonatoDetails.getPuntosPorDerrota());
    }
}
