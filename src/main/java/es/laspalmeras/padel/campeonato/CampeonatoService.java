package es.laspalmeras.padel.campeonato;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.campeonato.model.Campeonato;
import es.laspalmeras.padel.campeonato.model.dto.CampeonatoDTO;

public interface CampeonatoService {

    Campeonato saveCampeonato(Campeonato campeonato);

    List<CampeonatoDTO> findAllCampeonatos();

    Optional<CampeonatoDTO> findCampeonatoById(Long id);

    void deleteCampeonato(Long id);

    CampeonatoDTO createCampeonato(CampeonatoDTO campeonato);

    CampeonatoDTO updateCampeonato(Long id, CampeonatoDTO campeonatoDetails);

    void cambiarEstadoCampeonato(Long id, String nuevoEstado);
}
