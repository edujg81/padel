package es.laspalmeras.padel.business.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.business.service.model.Campeonato;

public interface CampeonatoService {

    Campeonato saveCampeonato(Campeonato campeonato);

    List<Campeonato> findAllCampeonatos();

    Optional<Campeonato> findCampeonatoById(Long id);

    void deleteCampeonato(Long id);

    Campeonato createCampeonato(Campeonato campeonato);

    Campeonato updateCampeonato(Long id, Campeonato campeonatoDetails);

    void cambiarEstadoCampeonato(Long id, String nuevoEstado);
}
