package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.InscripcionDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jugador;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-28T14:02:06+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
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
        if ( inscripcion == null ) {
            return null;
        }
        Campeonato campeonato = inscripcion.getCampeonato();
        if ( campeonato == null ) {
            return null;
        }
        Long id = campeonato.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long inscripcionJugadorId(Inscripcion inscripcion) {
        if ( inscripcion == null ) {
            return null;
        }
        Jugador jugador = inscripcion.getJugador();
        if ( jugador == null ) {
            return null;
        }
        Long id = jugador.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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
