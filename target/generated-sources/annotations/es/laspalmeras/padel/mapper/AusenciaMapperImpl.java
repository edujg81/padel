package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.AusenciaDTO;
import es.laspalmeras.padel.model.Ausencia;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T15:07:44+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class AusenciaMapperImpl implements AusenciaMapper {

    @Override
    public Ausencia toEntity(AusenciaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Ausencia ausencia = new Ausencia();

        ausencia.setPartido( AusenciaMapper.fromIdToPartido( dto.getPartidoId() ) );
        ausencia.setAusente( AusenciaMapper.fromIdToJugador( dto.getAusenteId() ) );
        ausencia.setSustituto( AusenciaMapper.fromIdToJugador( dto.getSustitutoId() ) );
        ausencia.setId( dto.getId() );

        return ausencia;
    }

    @Override
    public AusenciaDTO toDto(Ausencia entity) {
        if ( entity == null ) {
            return null;
        }

        AusenciaDTO ausenciaDTO = new AusenciaDTO();

        ausenciaDTO.setId( entity.getId() );

        ausenciaDTO.setPartidoId( entity.getPartido() != null ? entity.getPartido().getId() : null );
        ausenciaDTO.setAusenteId( entity.getAusente() != null ? entity.getAusente().getId() : null );
        ausenciaDTO.setSustitutoId( entity.getSustituto() != null ? entity.getSustituto().getId() : null );

        return ausenciaDTO;
    }
}
