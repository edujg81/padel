package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.InscripcionDTO;
import es.laspalmeras.padel.model.Campeonato;
import es.laspalmeras.padel.model.Inscripcion;
import es.laspalmeras.padel.model.Jugador;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-03T14:58:23+0100",
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
