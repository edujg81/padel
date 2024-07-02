package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-02T10:16:12+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class AusenciaMapperImpl implements AusenciaMapper {

    @Override
    public Ausencia toEntity(AusenciaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Ausencia ausencia = new Ausencia();

        ausencia.setPartido( ausenciaDTOToPartido( dto ) );
        ausencia.setAusente( ausenciaDTOToJugador( dto ) );
        ausencia.setSustituto( ausenciaDTOToJugador1( dto ) );
        ausencia.setId( dto.getId() );

        return ausencia;
    }

    @Override
    public AusenciaDTO toDto(Ausencia entity) {
        if ( entity == null ) {
            return null;
        }

        AusenciaDTO ausenciaDTO = new AusenciaDTO();

        ausenciaDTO.setPartidoId( entityPartidoId( entity ) );
        ausenciaDTO.setAusenteId( entityAusenteId( entity ) );
        ausenciaDTO.setSustitutoId( entitySustitutoId( entity ) );
        ausenciaDTO.setId( entity.getId() );

        return ausenciaDTO;
    }

    protected Partido ausenciaDTOToPartido(AusenciaDTO ausenciaDTO) {
        if ( ausenciaDTO == null ) {
            return null;
        }

        Partido partido = new Partido();

        partido.setId( ausenciaDTO.getPartidoId() );

        return partido;
    }

    protected Jugador ausenciaDTOToJugador(AusenciaDTO ausenciaDTO) {
        if ( ausenciaDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( ausenciaDTO.getAusenteId() );

        return jugador;
    }

    protected Jugador ausenciaDTOToJugador1(AusenciaDTO ausenciaDTO) {
        if ( ausenciaDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( ausenciaDTO.getSustitutoId() );

        return jugador;
    }

    private Long entityPartidoId(Ausencia ausencia) {
        if ( ausencia == null ) {
            return null;
        }
        Partido partido = ausencia.getPartido();
        if ( partido == null ) {
            return null;
        }
        Long id = partido.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityAusenteId(Ausencia ausencia) {
        if ( ausencia == null ) {
            return null;
        }
        Jugador ausente = ausencia.getAusente();
        if ( ausente == null ) {
            return null;
        }
        Long id = ausente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entitySustitutoId(Ausencia ausencia) {
        if ( ausencia == null ) {
            return null;
        }
        Jugador sustituto = ausencia.getSustituto();
        if ( sustituto == null ) {
            return null;
        }
        Long id = sustituto.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
