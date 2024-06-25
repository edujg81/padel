package es.laspalmeras.padel.business.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;

public interface PartidoService {

	Optional<Partido> read(Long id);
	
	Partido savePartido(Partido partido);

    List<Partido> getAllPartidos();

    //List<Partido> createPartidosForJornada(Long jornadaId, List<Long> jugadorIds);
    
    Long create(Partido partido);

    //void deletePartido(Long id);

    Partido getPartidoById(Long id);

    //Partido updatePartido(Long id, Partido partidoDetails);

    void registrarAusencia(Long partidoId, Long ausenteId, Long sustitutoId);

    List<Ausencia> getAusenciasByPartidoId(Long partidoId);
    
    List<Partido> createPartidosForJornada(Long jornadaId, List<Jugador> jugadores);

    Partido updatePartido(Long id, Partido partidoDetails);

    List<Partido> findAllPartidos();

    Partido findPartidoById(Long id);

    List<Partido> findPartidosByJornada(Long jornadaId);

    void deletePartido(Long id);
}
