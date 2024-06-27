package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.ClasificacionDTO;
import es.laspalmeras.padel.business.service.model.Clasificacion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T15:28:57+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class ClasificacionMapperImpl implements ClasificacionMapper {

    @Override
    public ClasificacionDTO toDto(Clasificacion clasificacion) {
        if ( clasificacion == null ) {
            return null;
        }

        ClasificacionDTO clasificacionDTO = new ClasificacionDTO();

        clasificacionDTO.setCampeonato( clasificacion.getCampeonato() );
        clasificacionDTO.setId( clasificacion.getId() );
        clasificacionDTO.setJuegosGanados( clasificacion.getJuegosGanados() );
        clasificacionDTO.setJuegosPerdidos( clasificacion.getJuegosPerdidos() );
        clasificacionDTO.setJugador( clasificacion.getJugador() );
        clasificacionDTO.setPartidosGanados( clasificacion.getPartidosGanados() );
        clasificacionDTO.setPartidosJugados( clasificacion.getPartidosJugados() );
        clasificacionDTO.setPartidosPerdidos( clasificacion.getPartidosPerdidos() );
        clasificacionDTO.setPosicion( clasificacion.getPosicion() );
        clasificacionDTO.setPuntos( clasificacion.getPuntos() );
        clasificacionDTO.setSetsGanados( clasificacion.getSetsGanados() );
        clasificacionDTO.setSetsPerdidos( clasificacion.getSetsPerdidos() );

        return clasificacionDTO;
    }

    @Override
    public Clasificacion toEntity(ClasificacionDTO clasificacionDTO) {
        if ( clasificacionDTO == null ) {
            return null;
        }

        Clasificacion clasificacion = new Clasificacion();

        clasificacion.setCampeonato( clasificacionDTO.getCampeonato() );
        clasificacion.setId( clasificacionDTO.getId() );
        clasificacion.setJuegosGanados( clasificacionDTO.getJuegosGanados() );
        clasificacion.setJuegosPerdidos( clasificacionDTO.getJuegosPerdidos() );
        clasificacion.setJugador( clasificacionDTO.getJugador() );
        clasificacion.setPartidosGanados( clasificacionDTO.getPartidosGanados() );
        clasificacion.setPartidosJugados( clasificacionDTO.getPartidosJugados() );
        clasificacion.setPartidosPerdidos( clasificacionDTO.getPartidosPerdidos() );
        clasificacion.setPosicion( clasificacionDTO.getPosicion() );
        clasificacion.setPuntos( clasificacionDTO.getPuntos() );
        clasificacion.setSetsGanados( clasificacionDTO.getSetsGanados() );
        clasificacion.setSetsPerdidos( clasificacionDTO.getSetsPerdidos() );

        return clasificacion;
    }
}
