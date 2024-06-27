package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.dto.InscripcionDTO;
import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.dto.JugadorDTO;
import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.business.service.model.Jugador;
import es.laspalmeras.padel.business.service.model.Partido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T15:22:27+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class AusenciaMapperImpl implements AusenciaMapper {

    @Override
    public AusenciaDTO toDto(Ausencia ausencia) {
        if ( ausencia == null ) {
            return null;
        }

        AusenciaDTO ausenciaDTO = new AusenciaDTO();

        ausenciaDTO.setId( ausencia.getId() );
        ausenciaDTO.setPartido( partidoToPartidoDTO( ausencia.getPartido() ) );
        ausenciaDTO.setAusente( jugadorToJugadorDTO( ausencia.getAusente() ) );
        ausenciaDTO.setSustituto( jugadorToJugadorDTO( ausencia.getSustituto() ) );

        return ausenciaDTO;
    }

    @Override
    public Ausencia toEntity(AusenciaDTO ausenciaDTO) {
        if ( ausenciaDTO == null ) {
            return null;
        }

        Ausencia ausencia = new Ausencia();

        ausencia.setId( ausenciaDTO.getId() );
        ausencia.setPartido( partidoDTOToPartido( ausenciaDTO.getPartido() ) );
        ausencia.setAusente( jugadorDTOToJugador( ausenciaDTO.getAusente() ) );
        ausencia.setSustituto( jugadorDTOToJugador( ausenciaDTO.getSustituto() ) );

        return ausencia;
    }

    protected List<AusenciaDTO> ausenciaListToAusenciaDTOList(List<Ausencia> list) {
        if ( list == null ) {
            return null;
        }

        List<AusenciaDTO> list1 = new ArrayList<AusenciaDTO>( list.size() );
        for ( Ausencia ausencia : list ) {
            list1.add( toDto( ausencia ) );
        }

        return list1;
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

    protected InscripcionDTO inscripcionToInscripcionDTO(Inscripcion inscripcion) {
        if ( inscripcion == null ) {
            return null;
        }

        InscripcionDTO inscripcionDTO = new InscripcionDTO();

        inscripcionDTO.setCampeonato( campeonatoToCampeonatoDTO( inscripcion.getCampeonato() ) );
        inscripcionDTO.setId( inscripcion.getId() );
        inscripcionDTO.setJugador( jugadorToJugadorDTO( inscripcion.getJugador() ) );

        return inscripcionDTO;
    }

    protected List<InscripcionDTO> inscripcionListToInscripcionDTOList(List<Inscripcion> list) {
        if ( list == null ) {
            return null;
        }

        List<InscripcionDTO> list1 = new ArrayList<InscripcionDTO>( list.size() );
        for ( Inscripcion inscripcion : list ) {
            list1.add( inscripcionToInscripcionDTO( inscripcion ) );
        }

        return list1;
    }

    protected List<JornadaDTO> jornadaListToJornadaDTOList(List<Jornada> list) {
        if ( list == null ) {
            return null;
        }

        List<JornadaDTO> list1 = new ArrayList<JornadaDTO>( list.size() );
        for ( Jornada jornada : list ) {
            list1.add( jornadaToJornadaDTO( jornada ) );
        }

        return list1;
    }

    protected CampeonatoDTO campeonatoToCampeonatoDTO(Campeonato campeonato) {
        if ( campeonato == null ) {
            return null;
        }

        CampeonatoDTO campeonatoDTO = new CampeonatoDTO();

        campeonatoDTO.setActivo( campeonato.getActivo() );
        campeonatoDTO.setCategoria( campeonato.getCategoria() );
        campeonatoDTO.setDivision( campeonato.getDivision() );
        campeonatoDTO.setEstado( campeonato.getEstado() );
        campeonatoDTO.setId( campeonato.getId() );
        campeonatoDTO.setInscripciones( inscripcionListToInscripcionDTOList( campeonato.getInscripciones() ) );
        campeonatoDTO.setJornadas( jornadaListToJornadaDTOList( campeonato.getJornadas() ) );
        campeonatoDTO.setPuntosPorDerrota( campeonato.getPuntosPorDerrota() );
        campeonatoDTO.setPuntosPorVictoria( campeonato.getPuntosPorVictoria() );
        campeonatoDTO.setYear( campeonato.getYear() );

        return campeonatoDTO;
    }

    protected List<PartidoDTO> partidoListToPartidoDTOList(List<Partido> list) {
        if ( list == null ) {
            return null;
        }

        List<PartidoDTO> list1 = new ArrayList<PartidoDTO>( list.size() );
        for ( Partido partido : list ) {
            list1.add( partidoToPartidoDTO( partido ) );
        }

        return list1;
    }

    protected JornadaDTO jornadaToJornadaDTO(Jornada jornada) {
        if ( jornada == null ) {
            return null;
        }

        JornadaDTO jornadaDTO = new JornadaDTO();

        jornadaDTO.setCampeonato( campeonatoToCampeonatoDTO( jornada.getCampeonato() ) );
        jornadaDTO.setFechaInicio( jornada.getFechaInicio() );
        jornadaDTO.setId( jornada.getId() );
        jornadaDTO.setNumero( jornada.getNumero() );
        jornadaDTO.setPartidos( partidoListToPartidoDTOList( jornada.getPartidos() ) );

        return jornadaDTO;
    }

    protected PartidoDTO partidoToPartidoDTO(Partido partido) {
        if ( partido == null ) {
            return null;
        }

        PartidoDTO partidoDTO = new PartidoDTO();

        partidoDTO.setAusencias( ausenciaListToAusenciaDTOList( partido.getAusencias() ) );
        partidoDTO.setEquipo1Jugador1( jugadorToJugadorDTO( partido.getEquipo1Jugador1() ) );
        partidoDTO.setEquipo1Jugador2( jugadorToJugadorDTO( partido.getEquipo1Jugador2() ) );
        partidoDTO.setEquipo2Jugador1( jugadorToJugadorDTO( partido.getEquipo2Jugador1() ) );
        partidoDTO.setEquipo2Jugador2( jugadorToJugadorDTO( partido.getEquipo2Jugador2() ) );
        partidoDTO.setEquipoGanador( partido.getEquipoGanador() );
        partidoDTO.setFecha( partido.getFecha() );
        partidoDTO.setId( partido.getId() );
        partidoDTO.setJornada( jornadaToJornadaDTO( partido.getJornada() ) );
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

    protected List<Ausencia> ausenciaDTOListToAusenciaList(List<AusenciaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Ausencia> list1 = new ArrayList<Ausencia>( list.size() );
        for ( AusenciaDTO ausenciaDTO : list ) {
            list1.add( toEntity( ausenciaDTO ) );
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

    protected Inscripcion inscripcionDTOToInscripcion(InscripcionDTO inscripcionDTO) {
        if ( inscripcionDTO == null ) {
            return null;
        }

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setCampeonato( campeonatoDTOToCampeonato( inscripcionDTO.getCampeonato() ) );
        inscripcion.setId( inscripcionDTO.getId() );
        inscripcion.setJugador( jugadorDTOToJugador( inscripcionDTO.getJugador() ) );

        return inscripcion;
    }

    protected List<Inscripcion> inscripcionDTOListToInscripcionList(List<InscripcionDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Inscripcion> list1 = new ArrayList<Inscripcion>( list.size() );
        for ( InscripcionDTO inscripcionDTO : list ) {
            list1.add( inscripcionDTOToInscripcion( inscripcionDTO ) );
        }

        return list1;
    }

    protected List<Jornada> jornadaDTOListToJornadaList(List<JornadaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Jornada> list1 = new ArrayList<Jornada>( list.size() );
        for ( JornadaDTO jornadaDTO : list ) {
            list1.add( jornadaDTOToJornada( jornadaDTO ) );
        }

        return list1;
    }

    protected Campeonato campeonatoDTOToCampeonato(CampeonatoDTO campeonatoDTO) {
        if ( campeonatoDTO == null ) {
            return null;
        }

        Campeonato campeonato = new Campeonato();

        campeonato.setActivo( campeonatoDTO.getActivo() );
        campeonato.setCategoria( campeonatoDTO.getCategoria() );
        campeonato.setDivision( campeonatoDTO.getDivision() );
        campeonato.setEstado( campeonatoDTO.getEstado() );
        campeonato.setId( campeonatoDTO.getId() );
        campeonato.setInscripciones( inscripcionDTOListToInscripcionList( campeonatoDTO.getInscripciones() ) );
        campeonato.setJornadas( jornadaDTOListToJornadaList( campeonatoDTO.getJornadas() ) );
        campeonato.setPuntosPorDerrota( campeonatoDTO.getPuntosPorDerrota() );
        campeonato.setPuntosPorVictoria( campeonatoDTO.getPuntosPorVictoria() );
        campeonato.setYear( campeonatoDTO.getYear() );

        return campeonato;
    }

    protected List<Partido> partidoDTOListToPartidoList(List<PartidoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Partido> list1 = new ArrayList<Partido>( list.size() );
        for ( PartidoDTO partidoDTO : list ) {
            list1.add( partidoDTOToPartido( partidoDTO ) );
        }

        return list1;
    }

    protected Jornada jornadaDTOToJornada(JornadaDTO jornadaDTO) {
        if ( jornadaDTO == null ) {
            return null;
        }

        Jornada jornada = new Jornada();

        jornada.setCampeonato( campeonatoDTOToCampeonato( jornadaDTO.getCampeonato() ) );
        jornada.setFechaInicio( jornadaDTO.getFechaInicio() );
        jornada.setId( jornadaDTO.getId() );
        jornada.setNumero( jornadaDTO.getNumero() );
        jornada.setPartidos( partidoDTOListToPartidoList( jornadaDTO.getPartidos() ) );

        return jornada;
    }

    protected Partido partidoDTOToPartido(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Partido partido = new Partido();

        partido.setAusencias( ausenciaDTOListToAusenciaList( partidoDTO.getAusencias() ) );
        partido.setEquipo1Jugador1( jugadorDTOToJugador( partidoDTO.getEquipo1Jugador1() ) );
        partido.setEquipo1Jugador2( jugadorDTOToJugador( partidoDTO.getEquipo1Jugador2() ) );
        partido.setEquipo2Jugador1( jugadorDTOToJugador( partidoDTO.getEquipo2Jugador1() ) );
        partido.setEquipo2Jugador2( jugadorDTOToJugador( partidoDTO.getEquipo2Jugador2() ) );
        partido.setEquipoGanador( partidoDTO.getEquipoGanador() );
        partido.setFecha( partidoDTO.getFecha() );
        partido.setId( partidoDTO.getId() );
        partido.setJornada( jornadaDTOToJornada( partidoDTO.getJornada() ) );
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
}
