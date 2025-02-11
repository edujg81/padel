package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.ClasificacionDTO;
import es.laspalmeras.padel.model.Clasificacion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T15:07:43+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ClasificacionMapperImpl implements ClasificacionMapper {

    @Override
    public ClasificacionDTO toDto(Clasificacion clasificacion) {
        if ( clasificacion == null ) {
            return null;
        }

        ClasificacionDTO clasificacionDTO = new ClasificacionDTO();

        clasificacionDTO.setId( clasificacion.getId() );
        clasificacionDTO.setPosicion( clasificacion.getPosicion() );
        clasificacionDTO.setPuntos( clasificacion.getPuntos() );
        clasificacionDTO.setPartidosJugados( clasificacion.getPartidosJugados() );
        clasificacionDTO.setPartidosGanados( clasificacion.getPartidosGanados() );
        clasificacionDTO.setPartidosPerdidos( clasificacion.getPartidosPerdidos() );
        clasificacionDTO.setSetsGanados( clasificacion.getSetsGanados() );
        clasificacionDTO.setSetsPerdidos( clasificacion.getSetsPerdidos() );
        clasificacionDTO.setJuegosGanados( clasificacion.getJuegosGanados() );
        clasificacionDTO.setJuegosPerdidos( clasificacion.getJuegosPerdidos() );

        clasificacionDTO.setCampeonatoId( clasificacion.getCampeonato() != null ? clasificacion.getCampeonato().getId() : null );
        clasificacionDTO.setJugadorId( clasificacion.getJugador() != null ? clasificacion.getJugador().getId() : null );

        return clasificacionDTO;
    }

    @Override
    public Clasificacion toEntity(ClasificacionDTO clasificacionDTO) {
        if ( clasificacionDTO == null ) {
            return null;
        }

        Clasificacion clasificacion = new Clasificacion();

        clasificacion.setCampeonato( ClasificacionMapper.fromIdToCampeonato( clasificacionDTO.getCampeonatoId() ) );
        clasificacion.setJugador( ClasificacionMapper.fromIdToJugador( clasificacionDTO.getJugadorId() ) );
        clasificacion.setId( clasificacionDTO.getId() );
        clasificacion.setPosicion( clasificacionDTO.getPosicion() );
        clasificacion.setPuntos( clasificacionDTO.getPuntos() );
        clasificacion.setPartidosJugados( clasificacionDTO.getPartidosJugados() );
        clasificacion.setPartidosGanados( clasificacionDTO.getPartidosGanados() );
        clasificacion.setPartidosPerdidos( clasificacionDTO.getPartidosPerdidos() );
        clasificacion.setSetsGanados( clasificacionDTO.getSetsGanados() );
        clasificacion.setSetsPerdidos( clasificacionDTO.getSetsPerdidos() );
        clasificacion.setJuegosGanados( clasificacionDTO.getJuegosGanados() );
        clasificacion.setJuegosPerdidos( clasificacionDTO.getJuegosPerdidos() );

        return clasificacion;
    }
}
