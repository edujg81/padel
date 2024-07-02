package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.business.service.InscripcionService;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.integration.repository.CampeonatoRepository;
import es.laspalmeras.padel.integration.repository.InscripcionRepository;
import es.laspalmeras.padel.integration.repository.JugadorRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

/**
 * Servicio para gestionar las inscripciones.
 */
@Service
public class InscripcionServiceImpl implements InscripcionService{

    @Autowired
    private InscripcionRepository inscripcionRepository;
    
    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;
    
    /**
     * Inscribir un jugador en un campeonato.
     *
     * @param campeonatoId el id del campeonato
     * @param jugadorId el id del jugador
     * @return la inscripción creada
     * @throws ResourceNotFoundException si el campeonato o el jugador no se encuentran
     * @throws IllegalArgumentException si el jugador no puede ser inscrito
     */
    @Override
    @Transactional
    public Inscripcion inscribirJugador(Long campeonatoId, Long jugadorId) {
        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con id: " + campeonatoId));
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con id: " + jugadorId));

        validarInscripcion(campeonato, jugador);
        
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setCampeonato(campeonato);
        nuevaInscripcion.setJugador(jugador);

        return inscripcionRepository.save(nuevaInscripcion);
    }
    
    @Override
    @Transactional
    public void desinscribirJugador(Long inscripcionId) {
        Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id: " + inscripcionId));
        inscripcionRepository.delete(inscripcion);
    }
    
    @Override
    @Transactional
    public List<Inscripcion> findAllInscripciones() {
        return inscripcionRepository.findAll();
    }
    
    @Override
    @Transactional
    public Optional<Inscripcion> findInscripcionById(Long id) {
        return inscripcionRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Inscripcion> findInscripcionesByCampeonato(Long campeonatoId) {
        return inscripcionRepository.findByCampeonatoId(campeonatoId);
    }

    @Override
    @Transactional
    public List<Inscripcion> findInscripcionesByJugador(Long jugadorId) {
        return inscripcionRepository.findByJugadorId(jugadorId);
    }
    
    @Override
    @Transactional
    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    @Transactional
    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
    
    private void validarInscripcion(Campeonato campeonato, Jugador jugador) {
        if (!campeonato.getActivo() || !"Sin iniciar".equals(campeonato.getEstado())) {
            throw new IllegalArgumentException("El campeonato no está activo o no está en estado 'Sin iniciar'.");
        }

        if ("Baja".equals(jugador.getEstado()) || jugador.getLesionado()) {
            throw new IllegalArgumentException("El jugador está dado de baja o lesionado.");
        }

        if (("Masculino".equals(campeonato.getCategoria()) && !"Masculino".equals(jugador.getSexo())) ||
                ("Femenino".equals(campeonato.getCategoria()) && !"Femenino".equals(jugador.getSexo()))) {
            throw new IllegalArgumentException("El jugador no puede inscribirse en un campeonato de diferente categoría.");
        }

        if (inscripcionRepository.countByCampeonatoId(campeonato.getId()) >= 20) {
            throw new IllegalArgumentException("El campeonato ya tiene 20 jugadores inscritos.");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByJugadorIdAndCampeonatoYearAndCampeonatoCategoria(
                jugador.getId(), campeonato.getYear(), campeonato.getCategoria());

        for (Inscripcion inscripcion : inscripciones) {
            if (!inscripcion.getCampeonato().getDivision().equals(campeonato.getDivision())) {
                throw new IllegalArgumentException("El jugador ya está inscrito en otra división de la misma categoría para el mismo año.");
            }
        }
    }
}
