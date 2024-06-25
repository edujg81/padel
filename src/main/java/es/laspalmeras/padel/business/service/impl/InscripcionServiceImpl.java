package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.InscripcionService;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.integration.repository.CampeonatoRepository;
import es.laspalmeras.padel.integration.repository.InscripcionRepository;
import es.laspalmeras.padel.integration.repository.JugadorRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

@Service
public class InscripcionServiceImpl implements InscripcionService{

    @Autowired
    private InscripcionRepository inscripcionRepository;
    
    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;
    
    @Override
    public Inscripcion inscribirJugador(Long campeonatoId, Long jugadorId) {
        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con id: " + campeonatoId));
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con id: " + jugadorId));

        // Validaciones
        if (!campeonato.getActivo() || !campeonato.getEstado().equals("Sin iniciar")) {
            throw new IllegalArgumentException("El campeonato no está activo o no está en estado 'Sin iniciar'.");
        }

        if (jugador.getEstado().equals("Baja") || jugador.getLesionado()) {
            throw new IllegalArgumentException("El jugador está dado de baja o lesionado.");
        }

        if (campeonato.getCategoria().equals("Masculino") && !jugador.getSexo().equals("Masculino") ||
            campeonato.getCategoria().equals("Femenino") && !jugador.getSexo().equals("Femenino")) {
            throw new IllegalArgumentException("El jugador no puede inscribirse en un campeonato de diferente categoría.");
        }

        if (inscripcionRepository.countByCampeonatoId(campeonatoId) >= 20) {
            throw new IllegalArgumentException("El campeonato ya tiene 20 jugadores inscritos.");
        }

        if (inscripcionRepository.existsByCampeonatoIdAndJugadorId(campeonatoId, jugadorId)) {
            throw new IllegalArgumentException("El jugador ya está inscrito en este campeonato.");
        }

        // Verificar inscripción en la misma categoría y año
        List<Inscripcion> inscripciones = inscripcionRepository.findByJugadorIdAndCampeonatoYearAndCampeonatoCategoria(jugadorId, campeonato.getYear(), campeonato.getCategoria());
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getCampeonato().getDivision() != campeonato.getDivision()) {
                throw new IllegalArgumentException("El jugador ya está inscrito en otra división de la misma categoría para el mismo año.");
            }
        }
        
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setCampeonato(campeonato);
        nuevaInscripcion.setJugador(jugador);

        return saveInscripcion(nuevaInscripcion);
    }
    
    @Override
    public void desinscribirJugador(Long inscripcionId) {
        Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id: " + inscripcionId));
        inscripcionRepository.delete(inscripcion);
    }
    
    @Override
    public List<Inscripcion> findAllInscripciones() {
        return inscripcionRepository.findAll();
    }
    
    @Override
    public Optional<Inscripcion> findInscripcionById(Long id) {
        return inscripcionRepository.findById(id);
    }

    @Override
    public List<Inscripcion> findInscripcionesByCampeonato(Long campeonatoId) {
        return inscripcionRepository.findByCampeonatoId(campeonatoId);
    }

    @Override
    public List<Inscripcion> findInscripcionesByJugador(Long jugadorId) {
        return inscripcionRepository.findByJugadorId(jugadorId);
    }
    
    @Override
    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
