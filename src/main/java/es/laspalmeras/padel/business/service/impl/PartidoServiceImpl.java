package es.laspalmeras.padel.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.laspalmeras.padel.business.service.PartidoService;
import es.laspalmeras.padel.business.service.model.Partido;
import es.laspalmeras.padel.integration.repository.PartidoRepository;

public class PartidoServiceImpl implements PartidoService{

	@Autowired
    private PartidoRepository partidoRepository;
	
	@Override
	public Partido savePartido(Partido partido) {
		return partidoRepository.save(partido);
	}

	@Override
	public List<Partido> findAllPartidos() {
		return partidoRepository.findAll();
	}

	@Override
	public List<Partido> createPartidosForJornada(Long jornadaId, List<Long> jugadorIds) {
		// Lógica para crear los partidos de una jornada con los jugadores dados
        // Dividir los jugadores en equipos y asignar partidos
        return null; // Implementar lógica
	}

}
