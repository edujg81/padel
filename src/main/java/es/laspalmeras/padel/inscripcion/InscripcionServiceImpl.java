package es.laspalmeras.padel.inscripcion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.campeonato.CampeonatoRepository;
import es.laspalmeras.padel.campeonato.model.Campeonato;
import es.laspalmeras.padel.exception.ResourceNotFoundException;
import es.laspalmeras.padel.inscripcion.model.Inscripcion;
import es.laspalmeras.padel.jugador.JugadorRepository;
import es.laspalmeras.padel.jugador.model.Jugador;

/**
 * Implementación del servicio de gestión de inscripciones.
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
     * Inscribe un jugador en un campeonato.
     *
     * @param campeonatoId el ID del campeonato
     * @param jugadorId el ID del jugador
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
    
    /**
     * Elimina la inscripción a un campeonato de un jugador por sus IDs .
     *
     * @param campeonatoId el ID del campeonato
     * @param jugadorId el ID del jugador
     */
    @Override
    @Transactional
    public void desinscribirJugador(Long campeonatoId, Long jugadorId) {
        Inscripcion inscripcion = inscripcionRepository.findByCampeonatoIdAndJugadorId(campeonatoId, jugadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id de campeonato: " + campeonatoId + " e id de jugador: " + jugadorId));
        inscripcionRepository.delete(inscripcion);
    }
    
    /**
     * Obtiene todas las inscripciones.
     *
     * @return una lista de inscripciones
     */
    @Override
    @Transactional
    public List<Inscripcion> findAllInscripciones() {
        return inscripcionRepository.findAll();
    }
    
    /**
     * Obtiene una inscripción por su ID.
     *
     * @param id el ID de la inscripción
     * @return un Optional con la inscripción si se encuentra
     */
    @Override
    @Transactional
    public Optional<Inscripcion> findInscripcionById(Long id) {
        return inscripcionRepository.findById(id);
    }
    
    /**
     * Obtiene las inscripciones a un campeonato por su ID.
     *
     * @param campeonatoId el ID del campeonato
     * @return una Lista con las inscripciones al campeonato
     */
    @Override
    @Transactional
    public List<Inscripcion> findInscripcionesByCampeonato(Long campeonatoId) {
        return inscripcionRepository.findByCampeonatoId(campeonatoId);
    }

    /**
     * Obtiene las inscripciones de un jugador por su ID.
     *
     * @param jugadorId el ID del jugador
     * @return una Lista con las inscripciones del jugador
     */
    @Override
    @Transactional
    public List<Inscripcion> findInscripcionesByJugador(Long jugadorId) {
        return inscripcionRepository.findByJugadorId(jugadorId);
    }
    
    /**
     * Guarda una inscripción
     *
     * @param inscripcion el objeto con la inscripción que se va a guardar
     * @return un objeto Inscripcion con la inscripción guardada
     */
    @Override
    @Transactional
    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    /**
     * Borra una inscripción
     *
     * @param id el ID de la inscripcion
     */
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

    /**
     * Obtiene una inscripción por campeonato(ID) y jugador(ID)
     *
     * @param campeonatoId el ID del campeonato
     * @param jugadorId el ID del jugador
     */
	@Override
	public Optional<Inscripcion> findInscripcionByCampeonatoAndJugador(Long campeonatoId, Long jugadorId) {
		return inscripcionRepository.findByCampeonatoIdAndJugadorId(campeonatoId, jugadorId);
	}
}
