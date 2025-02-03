package es.laspalmeras.padel.service;

import java.util.List;

import es.laspalmeras.padel.dto.ClasificacionDTO;

public interface ClasificacionService {

	List<ClasificacionDTO> findClasificacionByCampeonatoId(Long campeonatoId);
	List<ClasificacionDTO> obtenerClasificacionOrdenada(Long campeonatoId);

}
