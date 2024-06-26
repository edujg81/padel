package es.laspalmeras.padel.business.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.JugadorService;
import es.laspalmeras.padel.business.service.dto.JugadorDTO;
import es.laspalmeras.padel.business.service.mapper.JugadorMapper;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.integration.repository.JugadorRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

@Service
public class JugadorServiceImpl implements JugadorService{

	@Autowired
    private JugadorRepository jugadorRepository;
	
	private final JugadorMapper jugadorMapper = JugadorMapper.INSTANCE;
	
	@Override
	public Long create(JugadorDTO jugadorDTO) {
		Jugador jugador = jugadorMapper.toEntity(jugadorDTO);
		jugador.setFechaAlta(LocalDate.now());
	    jugador.setEstado("Alta");
	    jugadorMapper.toDto(jugadorRepository.save(jugador));
		return jugador.getId();
	}

	@Override
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
	public JugadorDTO getJugadorById(Long id) {
		Optional<Jugador> jugador = jugadorRepository.findById(id);
        return jugador.map(jugadorMapper::toDto).orElse(null);
	}
	
	@Override
	public JugadorDTO getJugadorByDni(String dni) {
		Optional<Jugador> jugador = jugadorRepository.findByDni(dni);
        return jugador.map(jugadorMapper::toDto).orElse(null);
	}

	@Override
	public JugadorDTO saveJugador(JugadorDTO jugadorDTO) {
		Jugador jugador = jugadorMapper.toEntity(jugadorDTO);
		jugador = jugadorRepository.save(jugador);
		return jugadorMapper.toDto(jugador);
	}

	@Override
    public JugadorDTO updateJugador(Long id, JugadorDTO jugadorDetails) {
		Jugador jugador = jugadorMapper.toEntity(jugadorDetails);
		jugador = jugadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        jugador.setDni(jugadorDetails.getDni());
        jugador.setNombreCompleto(jugadorDetails.getNombreCompleto());
        jugador.setTelefono(jugadorDetails.getTelefono());
        jugador.setEmail(jugadorDetails.getEmail());
        jugador.setSexo(jugadorDetails.getSexo());
        jugador.setEstado(jugadorDetails.getEstado());
        jugador.setLesionado(jugadorDetails.getLesionado());
        jugador = jugadorRepository.save(jugador);
        return jugadorMapper.toDto(jugador);
    }
	
	@Override
    public void darDeBajaJugadorPorId(Long id) {
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        if (jugador != null && jugador.getEstado().equals("Alta")) {
        	jugador.setEstado("Baja");
        	jugador.setFechaBaja(LocalDate.now());
        }
        jugadorRepository.save(jugador);
    }
	
	@Override
    public void darDeBajaJugadorPorDni(String dni) {
        Jugador jugador = jugadorRepository.findByDni(dni).orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        if (jugador != null && jugador.getEstado().equals("Alta")) {
        	jugador.setEstado("Baja");
        	jugador.setFechaBaja(LocalDate.now());
        }
        jugadorRepository.save(jugador);
    }
	
	@Override
	@Scheduled(cron = "0 0 0 * * ?")
    public void eliminarJugadoresBajaMasDeCincoAnios() {
        LocalDate cincoAniosAtras = LocalDate.now().minusYears(5);
        List<Jugador> jugadoresParaEliminar = jugadorRepository.findByFechaBajaBeforeAndEstado(cincoAniosAtras, "Baja");
        jugadorRepository.deleteAll(jugadoresParaEliminar);
    }

	@Override
	public Optional<JugadorDTO> read(Long id) {
		Optional<Jugador> jugador = jugadorRepository.findById(id);
        return Optional.ofNullable(jugador.map(jugadorMapper::toDto).orElse(null));
	}
}
