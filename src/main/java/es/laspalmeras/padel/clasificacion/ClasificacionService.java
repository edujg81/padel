package es.laspalmeras.padel.clasificacion;

import java.util.List;

import es.laspalmeras.padel.clasificacion.model.dto.ClasificacionDTO;

public interface ClasificacionService {

	List<ClasificacionDTO> findClasificacionByCampeonatoId(Long campeonatoId);
	List<ClasificacionDTO> obtenerClasificacionOrdenada(Long campeonatoId);

}
