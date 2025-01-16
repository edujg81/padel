package es.laspalmeras.padel.business.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.laspalmeras.padel.business.service.ClasificacionService;
import es.laspalmeras.padel.business.service.dto.ClasificacionDTO;
import es.laspalmeras.padel.business.service.model.Clasificacion;
import es.laspalmeras.padel.integration.repository.ClasificacionRepository;

@Service
public class ClasificacionServiceImpl implements ClasificacionService {

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Override
    @Transactional
    public List<ClasificacionDTO> findClasificacionByCampeonatoId(Long campeonatoId) {
        return clasificacionRepository.findByCampeonatoIdOrderByPuntosDesc(campeonatoId)
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
}
