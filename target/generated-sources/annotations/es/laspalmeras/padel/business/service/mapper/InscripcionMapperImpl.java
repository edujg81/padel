package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.InscripcionDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jugador;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T11:05:21+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class InscripcionMapperImpl implements InscripcionMapper {

    @Override
    public InscripcionDTO toDto(Inscripcion inscripcion) {
        if ( inscripcion == null ) {
            return null;
        }

        InscripcionDTO inscripcionDTO = new InscripcionDTO();

        inscripcionDTO.setCampeonatoId( inscripcionCampeonatoId( inscripcion ) );
        inscripcionDTO.setJugadorId( inscripcionJugadorId( inscripcion ) );
        inscripcionDTO.setId( inscripcion.getId() );

        return inscripcionDTO;
    }

    @Override
    public Inscripcion toEntity(InscripcionDTO inscripcionDTO) {
        if ( inscripcionDTO == null ) {
            return null;
        }

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setCampeonato( inscripcionDTOToCampeonato( inscripcionDTO ) );
        inscripcion.setJugador( inscripcionDTOToJugador( inscripcionDTO ) );
        inscripcion.setId( inscripcionDTO.getId() );

        return inscripcion;
    }

    private Long inscripcionCampeonatoId(Inscripcion inscripcion) {
        Campeonato campeonato = inscripcion.getCampeonato();
        if ( campeonato == null ) {
            return null;
        }
        return campeonato.getId();
    }

    private Long inscripcionJugadorId(Inscripcion inscripcion) {
        Jugador jugador = inscripcion.getJugador();
        if ( jugador == null ) {
            return null;
        }
        return jugador.getId();
    }

    protected Campeonato inscripcionDTOToCampeonato(InscripcionDTO inscripcionDTO) {
        if ( inscripcionDTO == null ) {
            return null;
        }

        Campeonato campeonato = new Campeonato();

        campeonato.setId( inscripcionDTO.getCampeonatoId() );

        return campeonato;
    }

    protected Jugador inscripcionDTOToJugador(InscripcionDTO inscripcionDTO) {
        if ( inscripcionDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( inscripcionDTO.getJugadorId() );

        return jugador;
    }
}
