package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.dto.InscripcionDTO;
import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.dto.JugadorDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.business.service.model.Jugador;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T15:27:02+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class CampeonatoMapperImpl implements CampeonatoMapper {

    @Autowired
    private JornadaMapper jornadaMapper;

    @Override
    public CampeonatoDTO toDto(Campeonato campeonato) {
        if ( campeonato == null ) {
            return null;
        }

        CampeonatoDTO campeonatoDTO = new CampeonatoDTO();

        campeonatoDTO.setJornadas( jornadaListToJornadaDTOList( campeonato.getJornadas() ) );
        campeonatoDTO.setActivo( campeonato.getActivo() );
        campeonatoDTO.setCategoria( campeonato.getCategoria() );
        campeonatoDTO.setDivision( campeonato.getDivision() );
        campeonatoDTO.setEstado( campeonato.getEstado() );
        campeonatoDTO.setId( campeonato.getId() );
        campeonatoDTO.setInscripciones( inscripcionListToInscripcionDTOList( campeonato.getInscripciones() ) );
        campeonatoDTO.setPuntosPorDerrota( campeonato.getPuntosPorDerrota() );
        campeonatoDTO.setPuntosPorVictoria( campeonato.getPuntosPorVictoria() );
        campeonatoDTO.setYear( campeonato.getYear() );

        return campeonatoDTO;
    }

    @Override
    public Campeonato toEntity(CampeonatoDTO campeonatoDTO) {
        if ( campeonatoDTO == null ) {
            return null;
        }

        Campeonato campeonato = new Campeonato();

        campeonato.setJornadas( jornadaDTOListToJornadaList( campeonatoDTO.getJornadas() ) );
        campeonato.setActivo( campeonatoDTO.getActivo() );
        campeonato.setCategoria( campeonatoDTO.getCategoria() );
        campeonato.setDivision( campeonatoDTO.getDivision() );
        campeonato.setEstado( campeonatoDTO.getEstado() );
        campeonato.setId( campeonatoDTO.getId() );
        campeonato.setInscripciones( inscripcionDTOListToInscripcionList( campeonatoDTO.getInscripciones() ) );
        campeonato.setPuntosPorDerrota( campeonatoDTO.getPuntosPorDerrota() );
        campeonato.setPuntosPorVictoria( campeonatoDTO.getPuntosPorVictoria() );
        campeonato.setYear( campeonatoDTO.getYear() );

        return campeonato;
    }

    protected List<JornadaDTO> jornadaListToJornadaDTOList(List<Jornada> list) {
        if ( list == null ) {
            return null;
        }

        List<JornadaDTO> list1 = new ArrayList<JornadaDTO>( list.size() );
        for ( Jornada jornada : list ) {
            list1.add( jornadaMapper.toDto( jornada ) );
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

        inscripcionDTO.setCampeonato( toDto( inscripcion.getCampeonato() ) );
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

    protected List<Jornada> jornadaDTOListToJornadaList(List<JornadaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Jornada> list1 = new ArrayList<Jornada>( list.size() );
        for ( JornadaDTO jornadaDTO : list ) {
            list1.add( jornadaMapper.toEntity( jornadaDTO ) );
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

        inscripcion.setCampeonato( toEntity( inscripcionDTO.getCampeonato() ) );
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
}
