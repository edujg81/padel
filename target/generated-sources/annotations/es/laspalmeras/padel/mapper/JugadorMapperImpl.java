package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.JugadorDTO;
import es.laspalmeras.padel.model.Jugador;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-03T14:58:36+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class JugadorMapperImpl implements JugadorMapper {

    @Override
    public JugadorDTO toDto(Jugador jugador) {
        if ( jugador == null ) {
            return null;
        }

        JugadorDTO jugadorDTO = new JugadorDTO();

        jugadorDTO.setDni( jugador.getDni() );
        jugadorDTO.setEmail( jugador.getEmail() );
        jugadorDTO.setEstado( jugador.getEstado() );
        jugadorDTO.setFechaAlta( jugador.getFechaAlta() );
        jugadorDTO.setFechaBaja( jugador.getFechaBaja() );
        jugadorDTO.setId( jugador.getId() );
        jugadorDTO.setLesionado( jugador.getLesionado() );
        jugadorDTO.setNombreCompleto( jugador.getNombreCompleto() );
        jugadorDTO.setSexo( jugador.getSexo() );
        jugadorDTO.setTelefono( jugador.getTelefono() );

        return jugadorDTO;
    }

    @Override
    public Jugador toEntity(JugadorDTO jugadorDTO) {
        if ( jugadorDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setDni( jugadorDTO.getDni() );
        jugador.setEmail( jugadorDTO.getEmail() );
        jugador.setEstado( jugadorDTO.getEstado() );
        jugador.setFechaAlta( jugadorDTO.getFechaAlta() );
        jugador.setFechaBaja( jugadorDTO.getFechaBaja() );
        jugador.setId( jugadorDTO.getId() );
        jugador.setLesionado( jugadorDTO.getLesionado() );
        jugador.setNombreCompleto( jugadorDTO.getNombreCompleto() );
        jugador.setSexo( jugadorDTO.getSexo() );
        jugador.setTelefono( jugadorDTO.getTelefono() );

        return jugador;
    }
}
