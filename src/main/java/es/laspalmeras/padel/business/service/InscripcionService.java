package es.laspalmeras.padel.business.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.business.service.model.Inscripcion;

public interface InscripcionService {

    Inscripcion saveInscripcion(Inscripcion inscripcion);

    List<Inscripcion> findAllInscripciones();

    void deleteInscripcion(Long id);
    
    Inscripcion inscribirJugador(Long campeonatoId, Long jugadorId);

    void desinscribirJugador(Long campeonatoId, Long jugadorId);

    Optional<Inscripcion> findInscripcionById(Long id);

    List<Inscripcion> findInscripcionesByCampeonato(Long campeonatoId);

    List<Inscripcion> findInscripcionesByJugador(Long jugadorId);

	Optional<Inscripcion> findInscripcionByCampeonatoAndJugador(Long campeonatoId, Long jugadorId);
}
