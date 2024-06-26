package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.dto.JugadorDTO;
import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T15:08:56+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
public class PartidoMapperImpl implements PartidoMapper {

    @Override
    public PartidoDTO toDto(Partido partido) {
        if ( partido == null ) {
            return null;
        }

        PartidoDTO partidoDTO = new PartidoDTO();

        partidoDTO.setAusencias( ausenciaListToAusenciaDTOList( partido.getAusencias() ) );
        partidoDTO.setEquipo1Jugador1( partido.getEquipo1Jugador1() );
        partidoDTO.setEquipo1Jugador2( partido.getEquipo1Jugador2() );
        partidoDTO.setEquipo2Jugador1( partido.getEquipo2Jugador1() );
        partidoDTO.setEquipo2Jugador2( partido.getEquipo2Jugador2() );
        partidoDTO.setEquipoGanador( partido.getEquipoGanador() );
        partidoDTO.setFecha( partido.getFecha() );
        partidoDTO.setId( partido.getId() );
        partidoDTO.setJornada( partido.getJornada() );
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

        partido.setAusencias( ausenciaDTOListToAusenciaList( partidoDTO.getAusencias() ) );
        partido.setEquipo1Jugador1( partidoDTO.getEquipo1Jugador1() );
        partido.setEquipo1Jugador2( partidoDTO.getEquipo1Jugador2() );
        partido.setEquipo2Jugador1( partidoDTO.getEquipo2Jugador1() );
        partido.setEquipo2Jugador2( partidoDTO.getEquipo2Jugador2() );
        partido.setEquipoGanador( partidoDTO.getEquipoGanador() );
        partido.setFecha( partidoDTO.getFecha() );
        partido.setId( partidoDTO.getId() );
        partido.setJornada( partidoDTO.getJornada() );
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

    protected JugadorDTO jugadorToJugadorDTO(Jugador jugador) {
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

    protected AusenciaDTO ausenciaToAusenciaDTO(Ausencia ausencia) {
        if ( ausencia == null ) {
            return null;
        }

        AusenciaDTO ausenciaDTO = new AusenciaDTO();

        ausenciaDTO.setAusente( jugadorToJugadorDTO( ausencia.getAusente() ) );
        ausenciaDTO.setId( ausencia.getId() );
        ausenciaDTO.setPartido( toDto( ausencia.getPartido() ) );
        ausenciaDTO.setSustituto( jugadorToJugadorDTO( ausencia.getSustituto() ) );

        return ausenciaDTO;
    }

    protected List<AusenciaDTO> ausenciaListToAusenciaDTOList(List<Ausencia> list) {
        if ( list == null ) {
            return null;
        }

        List<AusenciaDTO> list1 = new ArrayList<AusenciaDTO>( list.size() );
        for ( Ausencia ausencia : list ) {
            list1.add( ausenciaToAusenciaDTO( ausencia ) );
        }

        return list1;
    }

    protected Jugador jugadorDTOToJugador(JugadorDTO jugadorDTO) {
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

    protected Ausencia ausenciaDTOToAusencia(AusenciaDTO ausenciaDTO) {
        if ( ausenciaDTO == null ) {
            return null;
        }

        Ausencia ausencia = new Ausencia();

        ausencia.setAusente( jugadorDTOToJugador( ausenciaDTO.getAusente() ) );
        ausencia.setId( ausenciaDTO.getId() );
        ausencia.setPartido( toEntity( ausenciaDTO.getPartido() ) );
        ausencia.setSustituto( jugadorDTOToJugador( ausenciaDTO.getSustituto() ) );

        return ausencia;
    }

    protected List<Ausencia> ausenciaDTOListToAusenciaList(List<AusenciaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Ausencia> list1 = new ArrayList<Ausencia>( list.size() );
        for ( AusenciaDTO ausenciaDTO : list ) {
            list1.add( ausenciaDTOToAusencia( ausenciaDTO ) );
        }

        return list1;
    }
}
