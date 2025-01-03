package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.JugadorDTO;
import es.laspalmeras.padel.business.service.model.Jugador;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-30T13:14:57+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class JugadorMapperImpl implements JugadorMapper {

    @Override
    public JugadorDTO toDto(Jugador jugador) {
        if ( jugador == null ) {
            return null;
        }

        String nombreCompleto = null;
        Long id = null;

        nombreCompleto = jugador.getNombreCompleto();
        id = jugador.getId();

        JugadorDTO jugadorDTO = new JugadorDTO( id, nombreCompleto );

        jugadorDTO.setDni( jugador.getDni() );
        jugadorDTO.setTelefono( jugador.getTelefono() );
        jugadorDTO.setEmail( jugador.getEmail() );
        jugadorDTO.setSexo( jugador.getSexo() );
        jugadorDTO.setEstado( jugador.getEstado() );
        jugadorDTO.setLesionado( jugador.getLesionado() );
        jugadorDTO.setFechaAlta( jugador.getFechaAlta() );
        jugadorDTO.setFechaBaja( jugador.getFechaBaja() );

        return jugadorDTO;
    }

    @Override
    public Jugador toEntity(JugadorDTO jugadorDTO) {
        if ( jugadorDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setDni( jugadorDTO.getDni() );
        jugador.setNombreCompleto( jugadorDTO.getNombreCompleto() );
        jugador.setTelefono( jugadorDTO.getTelefono() );
        jugador.setEmail( jugadorDTO.getEmail() );
        jugador.setSexo( jugadorDTO.getSexo() );
        jugador.setEstado( jugadorDTO.getEstado() );
        jugador.setLesionado( jugadorDTO.getLesionado() );
        jugador.setFechaAlta( jugadorDTO.getFechaAlta() );
        jugador.setFechaBaja( jugadorDTO.getFechaBaja() );
        jugador.setId( jugadorDTO.getId() );

        return jugador;
    }
}
