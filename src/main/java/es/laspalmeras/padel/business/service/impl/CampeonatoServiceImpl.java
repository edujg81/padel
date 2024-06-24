package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.CampeonatoService;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.integration.repository.CampeonatoRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

@Service
public class CampeonatoServiceImpl implements CampeonatoService{
	
	@Autowired
    private CampeonatoRepository campeonatoRepository;
	
	@Override
	public Campeonato saveCampeonato(Campeonato campeonato) {
		return campeonatoRepository.save(campeonato);
	}

	@Override
	public List<Campeonato> findAllCampeonatos() {
		return campeonatoRepository.findAll();
	}

	@Override
	public Optional<Campeonato> findCampeonatoById(Long id) {
		return campeonatoRepository.findById(id);
	}

	@Override
	public void deleteCampeonato(Long id) {
		campeonatoRepository.deleteById(id);
	}

	@Override
	public Campeonato createCampeonato(Campeonato campeonato) {
		List<Campeonato> campeonatosActivos = campeonatoRepository.findByYearAndCategoriaAndDivisionAndActivoTrue(
	            campeonato.getYear(), campeonato.getCategoria(), campeonato.getDivision());

	    if (!campeonatosActivos.isEmpty()) {
	        throw new IllegalStateException("Ya existe un campeonato activo para esta categoría y división en el mismo año.");
	    }

	    return campeonatoRepository.save(campeonato);
	}

	@Override
	public Campeonato updateCampeonato(Long id, Campeonato campeonatoDetails) {
		Campeonato campeonato = campeonatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato", "id", id));

        campeonato.setYear(campeonatoDetails.getYear());
        campeonato.setCategoria(campeonatoDetails.getCategoria());
        campeonato.setDivision(campeonatoDetails.getDivision());
        campeonato.setEstado(campeonatoDetails.getEstado());
        campeonato.setActivo(campeonatoDetails.getActivo());
        campeonato.setPuntosPorVictoria(campeonatoDetails.getPuntosPorVictoria());
        campeonato.setPuntosPorDerrota(campeonatoDetails.getPuntosPorDerrota());

        return campeonatoRepository.save(campeonato);
	}

	@Override
	public void cambiarEstadoCampeonato(Long id, String nuevoEstado) {
		Campeonato campeonato = campeonatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato", "id", id));

        campeonato.setEstado(nuevoEstado);
        campeonatoRepository.save(campeonato);
	}

}
