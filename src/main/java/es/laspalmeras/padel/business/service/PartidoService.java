package es.laspalmeras.padel.business.service;

import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.dto.PartidoDTO;

public interface PartidoService {
	
	List<PartidoDTO> createPartidosForJornada(Long jornadaId);
		
	//Partido updatePartido(Long id, Partido partidoDetails);

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
