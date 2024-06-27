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
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T10:46:17+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class JornadaMapperImpl implements JornadaMapper {

    @Override
    public JornadaDTO toDto(Jornada jornada) {
        if ( jornada == null ) {
            return null;
        }

        JornadaDTO jornadaDTO = new JornadaDTO();

        jornadaDTO.setCampeonato( campeonatoToCampeonatoDTO( jornada.getCampeonato() ) );
        jornadaDTO.setFechaInicio( jornada.getFechaInicio() );
        jornadaDTO.setId( jornada.getId() );
        jornadaDTO.setNumero( jornada.getNumero() );

        return jornadaDTO;
    }

    @Override
    public Jornada toEntity(JornadaDTO jornadaDTO) {
        if ( jornadaDTO == null ) {
            return null;
        }

        Jornada jornada = new Jornada();

        jornada.setCampeonato( campeonatoDTOToCampeonato( jornadaDTO.getCampeonato() ) );
        jornada.setFechaInicio( jornadaDTO.getFechaInicio() );
        jornada.setId( jornadaDTO.getId() );
        jornada.setNumero( jornadaDTO.getNumero() );

        return jornada;
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
        campeonatoDTO.setPuntosPorDerrota( campeonato.getPuntosPorDerrota() );
        campeonatoDTO.setPuntosPorVictoria( campeonato.getPuntosPorVictoria() );
        campeonatoDTO.setYear( campeonato.getYear() );

        return campeonatoDTO;
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
        campeonato.setPuntosPorDerrota( campeonatoDTO.getPuntosPorDerrota() );
        campeonato.setPuntosPorVictoria( campeonatoDTO.getPuntosPorVictoria() );
        campeonato.setYear( campeonatoDTO.getYear() );

        return campeonato;
    }
}
