package es.laspalmeras.padel.partido;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.ausencia.model.dto.AusenciaDTO;
import es.laspalmeras.padel.partido.model.dto.PartidoDTO;

public interface PartidoService {
	
	List<PartidoDTO> createPartidosForJornada(Long jornadaId);

	List<PartidoDTO> getAllPartidos();
	
	PartidoDTO getPartidoById(Long id);
	
	List<PartidoDTO> getPartidosByJornada(Long jornadaId);
	
	void deletePartido(Long id);
	
	Optional<PartidoDTO> read(Long id);
	
	PartidoDTO savePartido(PartidoDTO partidoDTO);
	
	PartidoDTO updatePartido(Long id, PartidoDTO partidoDetails);

    void registrarAusencia(Long partidoId, Long ausenteId, Long sustitutoId);

    List<AusenciaDTO> getAusenciasByPartidoId(Long partidoId);
}
