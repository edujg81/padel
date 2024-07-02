package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-02T10:25:11+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class PartidoMapperImpl implements PartidoMapper {

    @Override
    public PartidoDTO toDto(Partido partido) {
        if ( partido == null ) {
            return null;
        }

        PartidoDTO partidoDTO = new PartidoDTO();

        partidoDTO.setJornadaId( partidoJornadaId( partido ) );
        partidoDTO.setEquipo1Jugador1Id( partidoEquipo1Jugador1Id( partido ) );
        partidoDTO.setEquipo1Jugador2Id( partidoEquipo1Jugador2Id( partido ) );
        partidoDTO.setEquipo2Jugador1Id( partidoEquipo2Jugador1Id( partido ) );
        partidoDTO.setEquipo2Jugador2Id( partidoEquipo2Jugador2Id( partido ) );
        partidoDTO.setEquipoGanador( partido.getEquipoGanador() );
        partidoDTO.setFecha( partido.getFecha() );
        partidoDTO.setId( partido.getId() );
        partidoDTO.setJuegosGanadosEquipo1Set1( partido.getJuegosGanadosEquipo1Set1() );
        partidoDTO.setJuegosGanadosEquipo1Set2( partido.getJuegosGanadosEquipo1Set2() );
        partidoDTO.setJuegosGanadosEquipo1Set3( partido.getJuegosGanadosEquipo1Set3() );
        partidoDTO.setJuegosGanadosEquipo2Set1( partido.getJuegosGanadosEquipo2Set1() );
        partidoDTO.setJuegosGanadosEquipo2Set2( partido.getJuegosGanadosEquipo2Set2() );
        partidoDTO.setJuegosGanadosEquipo2Set3( partido.getJuegosGanadosEquipo2Set3() );
        partidoDTO.setPista( partido.getPista() );
        partidoDTO.setRegistrado( partido.getRegistrado() );
        partidoDTO.setResultado( partido.getResultado() );
        partidoDTO.setSetsGanadosEquipo1( partido.getSetsGanadosEquipo1() );
        partidoDTO.setSetsGanadosEquipo2( partido.getSetsGanadosEquipo2() );

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
        partido.setEquipoGanador( partidoDTO.getEquipoGanador() );
        partido.setFecha( partidoDTO.getFecha() );
        partido.setId( partidoDTO.getId() );
        partido.setJuegosGanadosEquipo1Set1( partidoDTO.getJuegosGanadosEquipo1Set1() );
        partido.setJuegosGanadosEquipo1Set2( partidoDTO.getJuegosGanadosEquipo1Set2() );
        partido.setJuegosGanadosEquipo1Set3( partidoDTO.getJuegosGanadosEquipo1Set3() );
        partido.setJuegosGanadosEquipo2Set1( partidoDTO.getJuegosGanadosEquipo2Set1() );
        partido.setJuegosGanadosEquipo2Set2( partidoDTO.getJuegosGanadosEquipo2Set2() );
        partido.setJuegosGanadosEquipo2Set3( partidoDTO.getJuegosGanadosEquipo2Set3() );
        partido.setPista( partidoDTO.getPista() );
        partido.setRegistrado( partidoDTO.getRegistrado() );
        partido.setResultado( partidoDTO.getResultado() );
        partido.setSetsGanadosEquipo1( partidoDTO.getSetsGanadosEquipo1() );
        partido.setSetsGanadosEquipo2( partidoDTO.getSetsGanadosEquipo2() );

        return partido;
    }

    private Long partidoJornadaId(Partido partido) {
        if ( partido == null ) {
            return null;
        }
        Jornada jornada = partido.getJornada();
        if ( jornada == null ) {
            return null;
        }
        Long id = jornada.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long partidoEquipo1Jugador1Id(Partido partido) {
        if ( partido == null ) {
            return null;
        }
        Jugador equipo1Jugador1 = partido.getEquipo1Jugador1();
        if ( equipo1Jugador1 == null ) {
            return null;
        }
        Long id = equipo1Jugador1.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long partidoEquipo1Jugador2Id(Partido partido) {
        if ( partido == null ) {
            return null;
        }
        Jugador equipo1Jugador2 = partido.getEquipo1Jugador2();
        if ( equipo1Jugador2 == null ) {
            return null;
        }
        Long id = equipo1Jugador2.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long partidoEquipo2Jugador1Id(Partido partido) {
        if ( partido == null ) {
            return null;
        }
        Jugador equipo2Jugador1 = partido.getEquipo2Jugador1();
        if ( equipo2Jugador1 == null ) {
            return null;
        }
        Long id = equipo2Jugador1.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long partidoEquipo2Jugador2Id(Partido partido) {
        if ( partido == null ) {
            return null;
        }
        Jugador equipo2Jugador2 = partido.getEquipo2Jugador2();
        if ( equipo2Jugador2 == null ) {
            return null;
        }
        Long id = equipo2Jugador2.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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
