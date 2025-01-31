package es.laspalmeras.padel.clasificacion;

import java.util.List;

public interface ClasificacionService {

	List<ClasificacionDTO> findClasificacionByCampeonatoId(Long campeonatoId);
	List<ClasificacionDTO> obtenerClasificacionOrdenada(Long campeonatoId);

}
