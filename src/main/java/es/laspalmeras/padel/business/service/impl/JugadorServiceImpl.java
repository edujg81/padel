package es.laspalmeras.padel.business.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.JugadorService;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.integration.repository.JugadorRepository;
import es.laspalmeras.padel.presentation.config.exception.ResourceNotFoundException;

@Service
public class JugadorServiceImpl implements JugadorService{

	@Autowired
    private JugadorRepository jugadorRepository;
	
	@Override
	public Long create(Jugador jugador) {
		jugador.setFechaAlta(LocalDate.now());
	    jugador.setEstado("Alta");
	    return jugadorRepository.save(jugador).getId();
	}

	@Override
	public Optional<Jugador> read(Long id) {
		return jugadorRepository.findById(id);
	}

	@Override
	public void deleteJugador(Long id) {
		jugadorRepository.deleteById(id);
	}

	@Override
	public List<Jugador> getAllJugadores() {
		return jugadorRepository.findAll();
	}

	@Override
	public Jugador getJugadorById(Long id) {
		return jugadorRepository.findById(id).orElse(null);
	}
	
	@Override
	public Jugador getJugadorByDni(String dni) {
		return jugadorRepository.findByDni(dni).orElse(null);
	}

	@Override
	public Jugador saveJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	@Override
    public Jugador updateJugador(Long id, Jugador jugadorDetails) {
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        jugador.setDni(jugadorDetails.getDni());
        jugador.setNombreCompleto(jugadorDetails.getNombreCompleto());
        jugador.setTelefono(jugadorDetails.getTelefono());
        jugador.setEmail(jugadorDetails.getEmail());
        jugador.setSexo(jugadorDetails.getSexo());
        jugador.setEstado(jugadorDetails.getEstado());
        jugador.setLesionado(jugadorDetails.getLesionado());
        return jugadorRepository.save(jugador);
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
}
