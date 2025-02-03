package es.laspalmeras.padel.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.dto.CampeonatoDTO;
import es.laspalmeras.padel.model.Campeonato;

public interface CampeonatoService {

    Campeonato saveCampeonato(Campeonato campeonato);

    List<CampeonatoDTO> findAllCampeonatos();

    Optional<CampeonatoDTO> findCampeonatoById(Long id);

    void deleteCampeonato(Long id);

    CampeonatoDTO createCampeonato(CampeonatoDTO campeonato);

    CampeonatoDTO updateCampeonato(Long id, CampeonatoDTO campeonatoDetails);

    void cambiarEstadoCampeonato(Long id, String nuevoEstado);
}
