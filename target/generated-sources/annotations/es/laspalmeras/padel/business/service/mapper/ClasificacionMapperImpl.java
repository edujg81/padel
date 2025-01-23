package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.ClasificacionDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Clasificacion;
import es.laspalmeras.padel.business.service.model.Jugador;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-23T15:41:29+0100",
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

        clasificacionDTO.setCampeonatoId( clasificacionCampeonatoId( clasificacion ) );
        clasificacionDTO.setJugadorId( clasificacionJugadorId( clasificacion ) );
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

        clasificacion.setCampeonato( clasificacionDTOToCampeonato( clasificacionDTO ) );
        clasificacion.setJugador( clasificacionDTOToJugador( clasificacionDTO ) );
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

    protected Campeonato clasificacionDTOToCampeonato(ClasificacionDTO clasificacionDTO) {
        if ( clasificacionDTO == null ) {
            return null;
        }

        Campeonato campeonato = new Campeonato();

        campeonato.setId( clasificacionDTO.getCampeonatoId() );

        return campeonato;
    }

    protected Jugador clasificacionDTOToJugador(ClasificacionDTO clasificacionDTO) {
        if ( clasificacionDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( clasificacionDTO.getJugadorId() );

        return jugador;
    }
}
