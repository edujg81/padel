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
 * Servicio para la gestión de campeonatos.
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

	@Override
	@Transactional
	public List<CampeonatoDTO> findAllCampeonatos() {
		return campeonatoRepository.findAll().stream()
                .map(campeonatoMapper::toDto)
                .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Optional<CampeonatoDTO> findCampeonatoById(Long id) {
		return campeonatoRepository.findById(id).map(campeonatoMapper::toDto);
	}

	@Override
	@Transactional
	public void deleteCampeonato(Long id) {
		campeonatoRepository.deleteById(id);
	}

	/**
	 * Crea un nuevo campeonato.
	 * @param campeonatoDTO Datos del campeonato a crear.
	 * @return CampeonatoDTO del campeonato creado.
	 * @throws IllegalStateException si ya existe un campeonato activo para esta categoría y división en el mismo año.
	 */
	@Override
	@Transactional
	public CampeonatoDTO createCampeonato(CampeonatoDTO campeonatoDTO) {
		validarCampeonatoExistente(campeonatoDTO);
		
	    Campeonato campeonato = campeonatoMapper.toEntity(campeonatoDTO);
        saveCampeonato(campeonato);
        return campeonatoMapper.toDto(campeonato);
	}

	@Override
	@Transactional
	public CampeonatoDTO updateCampeonato(Long id, CampeonatoDTO campeonatoDetails) {
		Campeonato campeonato = campeonatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato", "id", id));

        actualizarDatosCampeonato(campeonato, campeonatoDetails);
        saveCampeonato(campeonato);
        return campeonatoMapper.toDto(campeonato);
	}

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
