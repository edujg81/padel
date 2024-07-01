package es.laspalmeras.padel.business.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.JornadaService;
import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.mapper.JornadaMapper;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;
import es.laspalmeras.padel.integration.repository.CampeonatoRepository;
import es.laspalmeras.padel.integration.repository.InscripcionRepository;
import es.laspalmeras.padel.integration.repository.JornadaRepository;
import es.laspalmeras.padel.integration.repository.PartidoRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class JornadaServiceImpl implements JornadaService {

	@Autowired
	private JornadaRepository jornadaRepository;
	
	@Autowired
	private CampeonatoRepository campeonatoRepository;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private InscripcionRepository inscripcionRepository;
	
	@Autowired
	private JornadaMapper jornadaMapper;

	@Override
	@Transactional
    public List<JornadaDTO> findAllJornadas() {
    	return jornadaRepository.findAll().stream()
    			.map(jornadaMapper::toDto)
    			.collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public Optional<JornadaDTO> findJornadaById(Long id) {
        return jornadaRepository.findById(id).map(jornadaMapper::toDto);
    }

    @Override
    @Transactional
    public List<JornadaDTO> findJornadasByCampeonato(Long campeonatoId) {
        return jornadaRepository.findByCampeonatoId(campeonatoId).stream()
        		.map(jornadaMapper::toDto)
        		.collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteJornada(Long id) {
        jornadaRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public JornadaDTO createJornada(Long campeonatoId, LocalDate fechaInicio) {
        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new ResourceNotFoundException("Campeonato no encontrado con id: " + campeonatoId));

        if (!"En curso".equals(campeonato.getEstado())) {
            throw new IllegalArgumentException("El campeonato debe estar 'En curso' para crear jornadas.");
        }

        if (jornadaRepository.existsByCampeonatoIdAndFechaInicio(campeonatoId, fechaInicio)) {
            throw new IllegalArgumentException("Ya existe una jornada con la misma fecha de inicio para este campeonato.");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByCampeonatoId(campeonatoId);
        int numInscritos = inscripciones.size();
        int numPartidos = numInscritos / 4;

        List<Partido> partidos = generarPartidos(inscripciones, numPartidos);

        Jornada nuevaJornada = new Jornada();
        nuevaJornada.setCampeonato(campeonato);
        nuevaJornada.setFechaInicio(fechaInicio);
        nuevaJornada.setNumero((int) (jornadaRepository.countByCampeonatoId(campeonatoId) + 1));
        
        Jornada savedJornada = jornadaRepository.save(nuevaJornada);

        partidos.forEach(partido -> {
            partido.setJornada(savedJornada);
            partidoRepository.save(partido);
        });

        return jornadaMapper.toDto(savedJornada);
    }
    
    @Transactional
    private List<Partido> generarPartidos(List<Inscripcion> inscripciones, int numPartidos) {
        List<Jugador> jugadores = inscripciones.stream()
                .map(Inscripcion::getJugador)
                .collect(Collectors.toList());

        return jugadores.stream().collect(Collectors.groupingBy(jugador -> jugadores.indexOf(jugador) / 4))
        		.values().stream().map(jugadoresGrupo -> {
        			Partido partido = new Partido();
                    partido.setEquipo1Jugador1(jugadoresGrupo.get(0));
                    partido.setEquipo1Jugador2(jugadores.get(3));
                    partido.setEquipo2Jugador1(jugadores.get(1));
                    partido.setEquipo2Jugador2(jugadores.get(2));
                    return partido;
        		}).collect(Collectors.toList());
    }
}