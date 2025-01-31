package es.laspalmeras.padel.clasificacion;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.campeonato.model.Campeonato;
import es.laspalmeras.padel.clasificacion.model.Clasificacion;
import es.laspalmeras.padel.clasificacion.model.dto.ClasificacionDTO;
import es.laspalmeras.padel.partido.model.Partido;

@Service
public class ClasificacionServiceImpl implements ClasificacionService {

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Override
    @Transactional
    public List<ClasificacionDTO> findClasificacionByCampeonatoId(Long campeonatoId) {
        return clasificacionRepository.findByCampeonatoIdOrderByPosicionAsc(campeonatoId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public List<ClasificacionDTO> obtenerClasificacionOrdenada(Long campeonatoId) {
        return clasificacionRepository.findClasificacionCompletaOrdenada(campeonatoId)
        		.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ClasificacionDTO toDto(Clasificacion clasificacion) {
        ClasificacionDTO dto = new ClasificacionDTO();
        dto.setId(clasificacion.getId());
        dto.setCampeonatoId(clasificacion.getCampeonato().getId());
        dto.setJugadorId(clasificacion.getJugador().getId());
        dto.setPosicion(clasificacion.getPosicion());
        dto.setPuntos(clasificacion.getPuntos());
        dto.setPartidosJugados(clasificacion.getPartidosJugados());
        dto.setPartidosGanados(clasificacion.getPartidosGanados());
        dto.setPartidosPerdidos(clasificacion.getPartidosPerdidos());
        dto.setSetsGanados(clasificacion.getSetsGanados());
        dto.setSetsPerdidos(clasificacion.getSetsPerdidos());
        dto.setJuegosGanados(clasificacion.getJuegosGanados());
        dto.setJuegosPerdidos(clasificacion.getJuegosPerdidos());
        return dto;
    }
    
    @Transactional
    public void actualizarPosiciones(Campeonato campeonato) {
        List<Clasificacion> clasificaciones = clasificacionRepository
            .findClasificacionCompletaOrdenada(campeonato.getId());

        int posicion = 1;
        for (Clasificacion c : clasificaciones) {
            c.setPosicion(posicion++);
        }
        
        clasificacionRepository.saveAll(clasificaciones);
    }
    
    // Método para usar después de actualizar resultados
    @Transactional
    public void procesarResultadoPartido(Partido partido) {
        // 1. Actualizar estadísticas de los jugadores
        // 2. Actualizar posiciones
        actualizarPosiciones(partido.getJornada().getCampeonato());
    }
}
