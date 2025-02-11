package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.InscripcionDTO;
import es.laspalmeras.padel.model.Inscripcion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T15:07:43+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class InscripcionMapperImpl implements InscripcionMapper {

    @Override
    public InscripcionDTO toDto(Inscripcion inscripcion) {
        if ( inscripcion == null ) {
            return null;
        }

        InscripcionDTO inscripcionDTO = new InscripcionDTO();

        inscripcionDTO.setId( inscripcion.getId() );

        inscripcionDTO.setCampeonatoId( inscripcion.getCampeonato() != null ? inscripcion.getCampeonato().getId() : null );
        inscripcionDTO.setJugadorId( inscripcion.getJugador() != null ? inscripcion.getJugador().getId() : null );

        return inscripcionDTO;
    }

    @Override
    public Inscripcion toEntity(InscripcionDTO inscripcionDTO) {
        if ( inscripcionDTO == null ) {
            return null;
        }

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setCampeonato( InscripcionMapper.fromIdToCampeonato( inscripcionDTO.getCampeonatoId() ) );
        inscripcion.setJugador( InscripcionMapper.fromIdToJugador( inscripcionDTO.getJugadorId() ) );
        inscripcion.setId( inscripcionDTO.getId() );

        return inscripcion;
    }
}
