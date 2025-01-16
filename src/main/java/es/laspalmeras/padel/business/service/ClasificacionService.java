package es.laspalmeras.padel.business.service;

import java.util.List;

import es.laspalmeras.padel.business.service.dto.ClasificacionDTO;

public interface ClasificacionService {

	List<ClasificacionDTO> findClasificacionByCampeonatoId(Long campeonatoId);

}
