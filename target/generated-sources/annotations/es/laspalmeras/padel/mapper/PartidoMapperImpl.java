package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.PartidoDTO;
import es.laspalmeras.padel.model.Jornada;
import es.laspalmeras.padel.model.Jugador;
import es.laspalmeras.padel.model.Partido;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T15:07:43+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class PartidoMapperImpl implements PartidoMapper {

    @Override
    public PartidoDTO toDto(Partido partido) {
        if ( partido == null ) {
            return null;
        }

        PartidoDTO partidoDTO = new PartidoDTO();

        partidoDTO.setId( partido.getId() );
        partidoDTO.setFecha( partido.getFecha() );
        partidoDTO.setPista( partido.getPista() );
        partidoDTO.setResultado( partido.getResultado() );
        partidoDTO.setEquipoGanador( partido.getEquipoGanador() );
        partidoDTO.setRegistrado( partido.getRegistrado() );
        partidoDTO.setJuegosGanadosEquipo1Set1( partido.getJuegosGanadosEquipo1Set1() );
        partidoDTO.setJuegosGanadosEquipo2Set1( partido.getJuegosGanadosEquipo2Set1() );
        partidoDTO.setJuegosGanadosEquipo1Set2( partido.getJuegosGanadosEquipo1Set2() );
        partidoDTO.setJuegosGanadosEquipo2Set2( partido.getJuegosGanadosEquipo2Set2() );
        partidoDTO.setJuegosGanadosEquipo1Set3( partido.getJuegosGanadosEquipo1Set3() );
        partidoDTO.setJuegosGanadosEquipo2Set3( partido.getJuegosGanadosEquipo2Set3() );
        partidoDTO.setSetsGanadosEquipo1( partido.getSetsGanadosEquipo1() );
        partidoDTO.setSetsGanadosEquipo2( partido.getSetsGanadosEquipo2() );

        partidoDTO.setJornadaId( partido.getJornada() != null ? partido.getJornada().getId() : null );
        partidoDTO.setEquipo1Jugador1Id( partido.getEquipo1Jugador1() != null ? partido.getEquipo1Jugador1().getId() : null );
        partidoDTO.setEquipo1Jugador2Id( partido.getEquipo1Jugador2() != null ? partido.getEquipo1Jugador2().getId() : null );
        partidoDTO.setEquipo2Jugador1Id( partido.getEquipo2Jugador1() != null ? partido.getEquipo2Jugador1().getId() : null );
        partidoDTO.setEquipo2Jugador2Id( partido.getEquipo2Jugador2() != null ? partido.getEquipo2Jugador2().getId() : null );

        return partidoDTO;
    }

    @Override
    public Partido toEntity(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Partido partido = new Partido();

        partido.setJornada( partidoDTOToJornada( partidoDTO ) );
        partido.setEquipo1Jugador1( partidoDTOToJugador( partidoDTO ) );
        partido.setEquipo1Jugador2( partidoDTOToJugador1( partidoDTO ) );
        partido.setEquipo2Jugador1( partidoDTOToJugador2( partidoDTO ) );
        partido.setEquipo2Jugador2( partidoDTOToJugador3( partidoDTO ) );
        partido.setId( partidoDTO.getId() );
        partido.setFecha( partidoDTO.getFecha() );
        partido.setPista( partidoDTO.getPista() );
        partido.setResultado( partidoDTO.getResultado() );
        partido.setEquipoGanador( partidoDTO.getEquipoGanador() );
        partido.setRegistrado( partidoDTO.getRegistrado() );
        partido.setJuegosGanadosEquipo1Set1( partidoDTO.getJuegosGanadosEquipo1Set1() );
        partido.setJuegosGanadosEquipo2Set1( partidoDTO.getJuegosGanadosEquipo2Set1() );
        partido.setJuegosGanadosEquipo1Set2( partidoDTO.getJuegosGanadosEquipo1Set2() );
        partido.setJuegosGanadosEquipo2Set2( partidoDTO.getJuegosGanadosEquipo2Set2() );
        partido.setJuegosGanadosEquipo1Set3( partidoDTO.getJuegosGanadosEquipo1Set3() );
        partido.setJuegosGanadosEquipo2Set3( partidoDTO.getJuegosGanadosEquipo2Set3() );
        partido.setSetsGanadosEquipo1( partidoDTO.getSetsGanadosEquipo1() );
        partido.setSetsGanadosEquipo2( partidoDTO.getSetsGanadosEquipo2() );

        return partido;
    }

    protected Jornada partidoDTOToJornada(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Jornada jornada = new Jornada();

        jornada.setId( partidoDTO.getJornadaId() );

        return jornada;
    }

    protected Jugador partidoDTOToJugador(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( partidoDTO.getEquipo1Jugador1Id() );

        return jugador;
    }

    protected Jugador partidoDTOToJugador1(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( partidoDTO.getEquipo1Jugador2Id() );

        return jugador;
    }

    protected Jugador partidoDTOToJugador2(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( partidoDTO.getEquipo2Jugador1Id() );

        return jugador;
    }

    protected Jugador partidoDTOToJugador3(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( partidoDTO.getEquipo2Jugador2Id() );

        return jugador;
    }
}
