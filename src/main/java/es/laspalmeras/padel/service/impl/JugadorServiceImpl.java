package es.laspalmeras.padel.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.dto.JugadorDTO;
import es.laspalmeras.padel.exception.DNIDuplicadoException;
import es.laspalmeras.padel.exception.ResourceNotFoundException;
import es.laspalmeras.padel.mapper.JugadorMapper;
import es.laspalmeras.padel.model.Campeonato;
import es.laspalmeras.padel.model.Jugador;
import es.laspalmeras.padel.repository.CampeonatoRepository;
import es.laspalmeras.padel.repository.JugadorRepository;
import es.laspalmeras.padel.service.JugadorService;

@Service
public class JugadorServiceImpl implements JugadorService{

	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private CampeonatoRepository campeonatoRepository;
	
	@Autowired
	private JugadorMapper jugadorMapper;
	
	public JugadorServiceImpl(JugadorRepository jugadorRepo, CampeonatoRepository campRepo, JugadorMapper jugadorMap) {
	    this.jugadorRepository = jugadorRepo;
	    this.campeonatoRepository = campRepo;
	    this.jugadorMapper = jugadorMap;
	}
	
	@Override
	@Transactional
	public Long create(JugadorDTO jugadorDTO) {
		if (jugadorRepository.findByDni(jugadorDTO.getDni()).isPresent()) {
	        throw new DNIDuplicadoException(jugadorDTO.getDni());
	    }
		Jugador jugador = jugadorMapper.toEntity(jugadorDTO);
		jugador.setFechaAlta(LocalDate.now());
	    jugador.setEstado("Alta");
	    jugadorRepository.save(jugador);
		return jugador.getId();
	}

	@Override
	@Transactional
	public void deleteJugador(Long id) {
		jugadorRepository.deleteById(id);
	}

	@Override
	public List<JugadorDTO> getAllJugadores() {
		return jugadorRepository.findAll().stream()
                .map(jugadorMapper::toDto)
                .collect(Collectors.toList());
	}

	@Override
	public Optional<JugadorDTO> getJugadorById(Long id) {
		Optional<Jugador> jugador = jugadorRepository.findById(id);
		return jugador.map(jugadorMapper::toDto);
	}
	
	@Override
	public JugadorDTO getJugadorByDni(String dni) {
		Optional<Jugador> jugador = jugadorRepository.findByDni(dni);
        return jugador.map(jugadorMapper::toDto).orElse(null);
	}

	@Override
	@Transactional
	public JugadorDTO saveJugador(JugadorDTO jugadorDTO) {
		Jugador jugador = jugadorMapper.toEntity(jugadorDTO);
		jugador = jugadorRepository.save(jugador);
		return jugadorMapper.toDto(jugador);
	}

	@Override
	@Transactional
	public JugadorDTO updateJugador(Long id, JugadorDTO jugadorDetails) {
	    Jugador jugador = jugadorRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
	    actualizarDatosJugador(jugador, jugadorDetails);
	    jugador = jugadorRepository.save(jugador);
	    return jugadorMapper.toDto(jugador);
	}
	
	private void actualizarDatosJugador(Jugador jugador, JugadorDTO jugadorDetails) {
	    jugador.setDni(jugadorDetails.getDni());
	    jugador.setNombreCompleto(jugadorDetails.getNombreCompleto());
	    jugador.setTelefono(jugadorDetails.getTelefono());
	    jugador.setEmail(jugadorDetails.getEmail());
	    jugador.setSexo(jugadorDetails.getSexo());
	    jugador.setEstado(jugadorDetails.getEstado());
	    jugador.setLesionado(jugadorDetails.getLesionado());
	}
	
    private void darDeBajaJugador(Jugador jugador) {
        if (jugador != null && "Alta".equals(jugador.getEstado())) {
        	jugador.setEstado("Baja");
        	jugador.setFechaBaja(LocalDate.now());
        	jugadorRepository.save(jugador);
        }
    }
	
	@Override
	@Transactional
    public void darDeBajaJugadorPorId(Long id) {
        Jugador jugador = jugadorRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        darDeBajaJugador(jugador);
    }
	
	@Override
	@Transactional
    public void darDeBajaJugadorPorDni(String dni) {
        Jugador jugador = jugadorRepository.findByDni(dni)
        		.orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        darDeBajaJugador(jugador);
    }
	
	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
    public void eliminarJugadoresBajaMasDeCincoAnios() {
        LocalDate cincoAniosAtras = LocalDate.now().minusYears(5);
        List<Jugador> jugadoresParaEliminar = jugadorRepository.findByFechaBajaBeforeAndEstado(cincoAniosAtras, "Baja");
        jugadorRepository.deleteAll(jugadoresParaEliminar);
    }

	/**
	 *
	 */
	@Override
	public List<JugadorDTO> getJugadoresDisponiblesParaCampeonato(Long campeonatoId) {
        // Obtener el campeonato por id
        Optional<Campeonato> campeonato = campeonatoRepository.findById(campeonatoId);
        
        // Verificar si el campeonato está presente
        if (campeonato.isPresent()) {
        	// Obtener jugadores que no están inscritos en campeonatos activos de la misma categoría y año
            List<Jugador> jugadoresDisponibles = jugadorRepository.findJugadoresDisponiblesParaCampeonato(
                    campeonato.get().getCategoria(), campeonato.get().getYear());
            
            // Convertir a DTO
            return jugadoresDisponibles.stream()
                    .map(jugador -> new JugadorDTO(jugador.getId(), jugador.getNombreCompleto()))
                    .collect(Collectors.toList());
        }
        else {
        	throw new RuntimeException("Campeonato no encontrado");
        }
    }
}
