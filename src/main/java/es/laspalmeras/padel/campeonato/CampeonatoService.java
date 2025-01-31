package es.laspalmeras.padel.campeonato;

import java.util.List;
import java.util.Optional;

public interface CampeonatoService {

    Campeonato saveCampeonato(Campeonato campeonato);

    List<CampeonatoDTO> findAllCampeonatos();

    Optional<CampeonatoDTO> findCampeonatoById(Long id);

    void deleteCampeonato(Long id);

    CampeonatoDTO createCampeonato(CampeonatoDTO campeonato);

    CampeonatoDTO updateCampeonato(Long id, CampeonatoDTO campeonatoDetails);

    void cambiarEstadoCampeonato(Long id, String nuevoEstado);
}
