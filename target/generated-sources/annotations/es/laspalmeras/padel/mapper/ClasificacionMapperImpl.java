package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.ClasificacionDTO;
import es.laspalmeras.padel.model.Campeonato;
import es.laspalmeras.padel.model.Clasificacion;
import es.laspalmeras.padel.model.Jugador;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-03T14:58:09+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ClasificacionMapperImpl implements ClasificacionMapper {

    @Override
    public ClasificacionDTO toDto(Clasificacion clasificacion) {
        if ( clasificacion == null ) {
            return null;
        }

        ClasificacionDTO clasificacionDTO = new ClasificacionDTO();

        Long id = clasificacionCampeonatoId( clasificacion );
        if ( id != null ) {
            clasificacionDTO.setCampeonatoId( id );
        }
        else {
            clasificacionDTO.setCampeonatoId( null );
        }
        Long id1 = clasificacionJugadorId( clasificacion );
        if ( id1 != null ) {
            clasificacionDTO.setJugadorId( id1 );
        }
        else {
            clasificacionDTO.setJugadorId( null );
        }
        clasificacionDTO.setId( clasificacion.getId() );
        clasificacionDTO.setJuegosGanados( clasificacion.getJuegosGanados() );
        clasificacionDTO.setJuegosPerdidos( clasificacion.getJuegosPerdidos() );
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

        clasificacion.setCampeonato( ClasificacionMapper.fromIdToCampeonato( clasificacionDTO.getCampeonatoId() ) );
        clasificacion.setJugador( ClasificacionMapper.fromIdToJugador( clasificacionDTO.getJugadorId() ) );
        clasificacion.setId( clasificacionDTO.getId() );
        clasificacion.setJuegosGanados( clasificacionDTO.getJuegosGanados() );
        clasificacion.setJuegosPerdidos( clasificacionDTO.getJuegosPerdidos() );
        clasificacion.setPartidosGanados( clasificacionDTO.getPartidosGanados() );
        clasificacion.setPartidosJugados( clasificacionDTO.getPartidosJugados() );
        clasificacion.setPartidosPerdidos( clasificacionDTO.getPartidosPerdidos() );
        clasificacion.setPosicion( clasificacionDTO.getPosicion() );
        clasificacion.setPuntos( clasificacionDTO.getPuntos() );
        clasificacion.setSetsGanados( clasificacionDTO.getSetsGanados() );
        clasificacion.setSetsPerdidos( clasificacionDTO.getSetsPerdidos() );

        return clasificacion;
    }

    private Long clasificacionCampeonatoId(Clasificacion clasificacion) {
        Campeonato campeonato = clasificacion.getCampeonato();
        if ( campeonato == null ) {
            return null;
        }
        return campeonato.getId();
    }

    private Long clasificacionJugadorId(Clasificacion clasificacion) {
        Jugador jugador = clasificacion.getJugador();
        if ( jugador == null ) {
            return null;
        }
        return jugador.getId();
    }
}
