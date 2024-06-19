package es.laspalmeras.padel.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.model.Partido;
import es.laspalmeras.padel.integration.repository.PartidoRepository;

@Service
public class PartidoService {

	@Autowired
	private PartidoRepository partidoRepository;

	public void deletePartido(Long id) {
		partidoRepository.deleteById(id);
	}

	public List<Partido> getAllPartidos() {
		return partidoRepository.findAll();
	}

	public Partido getPartidoById(Long id) {
		return partidoRepository.findById(id).orElse(null);
	}

	public Partido savePartido(Partido partido) {
		return partidoRepository.save(partido);
	}

	public Partido updatePartido(Long id, Partido partidoDetails) {
		Partido partido = partidoRepository.findById(id).orElse(null);
		if (partido != null) {
			partido.setFecha(partidoDetails.getFecha());
			partido.setPista(partidoDetails.getPista());
			partido.setResultado(partidoDetails.getResultado());
			partido.setJugadores(partidoDetails.getJugadores());
			return partidoRepository.save(partido);
		}
		return null;
	}
}
