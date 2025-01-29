package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.business.service.model.Partido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-29T11:53:36+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class JornadaMapperImpl implements JornadaMapper {

    @Autowired
    private PartidoMapper partidoMapper;

    @Override
    public JornadaDTO toDto(Jornada jornada) {
        if ( jornada == null ) {
            return null;
        }

        JornadaDTO jornadaDTO = new JornadaDTO();

        jornadaDTO.setCampeonatoId( jornadaCampeonatoId( jornada ) );
        jornadaDTO.setFechaInicio( jornada.getFechaInicio() );
        jornadaDTO.setId( jornada.getId() );
        jornadaDTO.setNumero( jornada.getNumero() );
        jornadaDTO.setPartidos( toPartidoDTOs( jornada.getPartidos() ) );

        return jornadaDTO;
    }

    @Override
    public Jornada toEntity(JornadaDTO jornadaDTO) {
        if ( jornadaDTO == null ) {
            return null;
        }

        Jornada jornada = new Jornada();

        jornada.setCampeonato( jornadaDTOToCampeonato( jornadaDTO ) );
        jornada.setFechaInicio( jornadaDTO.getFechaInicio() );
        jornada.setId( jornadaDTO.getId() );
        jornada.setNumero( jornadaDTO.getNumero() );
        jornada.setPartidos( toPartidos( jornadaDTO.getPartidos() ) );

        return jornada;
    }

    @Override
    public List<PartidoDTO> toPartidoDTOs(List<Partido> partidos) {
        if ( partidos == null ) {
            return null;
        }

        List<PartidoDTO> list = new ArrayList<PartidoDTO>( partidos.size() );
        for ( Partido partido : partidos ) {
            list.add( partidoMapper.toDto( partido ) );
        }

        return list;
    }

    @Override
    public List<Partido> toPartidos(List<PartidoDTO> partidoDTOs) {
        if ( partidoDTOs == null ) {
            return null;
        }

        List<Partido> list = new ArrayList<Partido>( partidoDTOs.size() );
        for ( PartidoDTO partidoDTO : partidoDTOs ) {
            list.add( partidoMapper.toEntity( partidoDTO ) );
        }

        return list;
    }

    private Long jornadaCampeonatoId(Jornada jornada) {
        Campeonato campeonato = jornada.getCampeonato();
        if ( campeonato == null ) {
            return null;
        }
        return campeonato.getId();
    }

    protected Campeonato jornadaDTOToCampeonato(JornadaDTO jornadaDTO) {
        if ( jornadaDTO == null ) {
            return null;
        }

        Campeonato campeonato = new Campeonato();

        campeonato.setId( jornadaDTO.getCampeonatoId() );

        return campeonato;
    }
}
