package es.laspalmeras.padel.services;

import es.laspalmeras.padel.exception.ResourceNotFoundException;
import es.laspalmeras.padel.models.*;
import es.laspalmeras.padel.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoService {
    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    // Métodos existentes...

    public Partido registrarResultadoPartido(Long partidoId, Partido resultado) {
        Partido partido = partidoRepository.findById(partidoId).orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));

        // Actualizar el partido con los resultados y datos proporcionados
        partido.setResultado(resultado.getResultado());
        partido.setPista(resultado.getPista());
        partido.setFecha(resultado.getFecha());
        partido.setAusente(resultado.getAusente());
        partido.setLesionado(resultado.getLesionado());
        partido.setSustituto(resultado.getSustituto());
        partido.setJuegosGanadosEquipo1(resultado.getJuegosGanadosEquipo1());
        partido.setJuegosPerdidosEquipo1(resultado.getJuegosPerdidosEquipo1());
        partido.setSetsGanadosEquipo1(resultado.getSetsGanadosEquipo1());
        partido.setSetsPerdidosEquipo1(resultado.getSetsPerdidosEquipo1());
        partido.setJuegosGanadosEquipo2(resultado.getJuegosGanadosEquipo2());
        partido.setJuegosPerdidosEquipo2(resultado.getJuegosPerdidosEquipo2());
        partido.setSetsGanadosEquipo2(resultado.getSetsGanadosEquipo2());
        partido.setSetsPerdidosEquipo2(resultado.getSetsPerdidosEquipo2());

        actualizarClasificacion(partido);
        return partidoRepository.save(partido);
    }

    private void actualizarClasificacion(Partido partido) {
        List<Clasificacion> clasificaciones = clasificacionRepository.findByCampeonatoOrderByPosicionAsc(partido.getJornada().getCampeonato());

        // Lógica para actualizar la clasificación basándose en los resultados del partido
        // Equipo 1
        Clasificacion clasificacionEquipo1Jugador1 = clasificaciones.stream()
                .filter(c -> c.getJugador().equals(partido.getEquipo1Jugador1()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

        Clasificacion clasificacionEquipo1Jugador2 = clasificaciones.stream()
                .filter(c -> c.getJugador().equals(partido.getEquipo1Jugador2()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

        // Equipo 2
        Clasificacion clasificacionEquipo2Jugador1 = clasificaciones.stream()
                .filter(c -> c.getJugador().equals(partido.getEquipo2Jugador1()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

        Clasificacion clasificacionEquipo2Jugador2 = clasificaciones.stream()
                .filter(c -> c.getJugador().equals(partido.getEquipo2Jugador2()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Clasificación no encontrada para el jugador"));

        // Actualizar la lógica de clasificación
        actualizarEstadisticas(clasificacionEquipo1Jugador1, partido, true);
        actualizarEstadisticas(clasificacionEquipo1Jugador2, partido, true);
        actualizarEstadisticas(clasificacionEquipo2Jugador1, partido, false);
        actualizarEstadisticas(clasificacionEquipo2Jugador2, partido, false);

        clasificacionRepository.save(clasificacionEquipo1Jugador1);
        clasificacionRepository.save(clasificacionEquipo1Jugador2);
        clasificacionRepository.save(clasificacionEquipo2Jugador1);
        clasificacionRepository.save(clasificacionEquipo2Jugador2);
    }

    private void actualizarEstadisticas(Clasificacion clasificacion, Partido partido, boolean esGanador) {
        clasificacion.setPartidosJugados(clasificacion.getPartidosJugados() + 1);
        if (esGanador) {
            clasificacion.setPartidosGanados(clasificacion.getPartidosGanados() + 1);
            clasificacion.setPuntos(clasificacion.getPuntos() + 2);
        } else {
            clasificacion.setPartidosPerdidos(clasificacion.getPartidosPerdidos() + 1);
        }

        clasificacion.setSetsGanados(clasificacion.getSetsGanados() + (esGanador ? partido.getSetsGanadosEquipo1() : partido.getSetsGanadosEquipo2()));
        clasificacion.setSetsPerdidos(clasificacion.getSetsPerdidos() + (esGanador ? partido.getSetsPerdidosEquipo1() : partido.getSetsPerdidosEquipo2()));
        clasificacion.setJuegosGanados(clasificacion.getJuegosGanados() + (esGanador ? partido.getJuegosGanadosEquipo1() : partido.getJuegosGanadosEquipo2()));
        clasificacion.setJuegosPerdidos(clasificacion.getJuegosPerdidos() + (esGanador ? partido.getJuegosPerdidosEquipo1() : partido.getJuegosPerdidosEquipo2()));
     
        // Actualizar diferencia de sets y juegos
        clasificacion.setDiferenciaSets(clasificacion.getSetsGanados() - clasificacion.getSetsPerdidos());
        clasificacion.setDiferenciaJuegos(clasificacion.getJuegosGanados() - clasificacion.getJuegosPerdidos());
    }
}