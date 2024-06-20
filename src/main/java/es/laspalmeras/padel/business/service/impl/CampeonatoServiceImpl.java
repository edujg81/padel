package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.laspalmeras.padel.business.service.CampeonatoService;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.integration.repository.CampeonatoRepository;

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

}
