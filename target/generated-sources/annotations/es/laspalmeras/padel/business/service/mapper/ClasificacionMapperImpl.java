package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.ClasificacionDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Clasificacion;
import es.laspalmeras.padel.business.service.model.Jugador;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-02T10:16:11+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
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
        clasificacionDTO.setPosicion( clasificacion.getPosicion() );
        clasificacionDTO.setPuntos( clasificacion.getPuntos() );
        clasificacionDTO.setPartidosJugados( clasificacion.getPartidosJugados() );
        clasificacionDTO.setPartidosGanados( clasificacion.getPartidosGanados() );
        clasificacionDTO.setPartidosPerdidos( clasificacion.getPartidosPerdidos() );
        clasificacionDTO.setSetsGanados( clasificacion.getSetsGanados() );
        clasificacionDTO.setSetsPerdidos( clasificacion.getSetsPerdidos() );
        clasificacionDTO.setJuegosGanados( clasificacion.getJuegosGanados() );
        clasificacionDTO.setJuegosPerdidos( clasificacion.getJuegosPerdidos() );

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

    private Long clasificacionCampeonatoId(Clasificacion clasificacion) {
        if ( clasificacion == null ) {
            return null;
        }
        Campeonato campeonato = clasificacion.getCampeonato();
        if ( campeonato == null ) {
            return null;
        }
        Long id = campeonato.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long clasificacionJugadorId(Clasificacion clasificacion) {
        if ( clasificacion == null ) {
            return null;
        }
        Jugador jugador = clasificacion.getJugador();
        if ( jugador == null ) {
            return null;
        }
        Long id = jugador.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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
