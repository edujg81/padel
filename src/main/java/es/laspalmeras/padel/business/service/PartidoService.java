package es.laspalmeras.padel.business.service;

import java.util.List;

import es.laspalmeras.padel.business.service.model.Partido;

public interface PartidoService {

    public Partido savePartido(Partido partido);

    public List<Partido> findAllPartidos();

    public List<Partido> createPartidosForJornada(Long jornadaId, List<Long> jugadorIds);
}
