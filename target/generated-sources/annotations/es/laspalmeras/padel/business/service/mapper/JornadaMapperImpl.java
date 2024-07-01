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
    date = "2024-07-01T09:34:42+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
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
        jornadaDTO.setPartidos( partidoListToPartidoDTOList( jornada.getPartidos() ) );

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
        jornada.setPartidos( partidoDTOListToPartidoList( jornadaDTO.getPartidos() ) );

        return jornada;
    }

    private Long jornadaCampeonatoId(Jornada jornada) {
        if ( jornada == null ) {
            return null;
        }
        Campeonato campeonato = jornada.getCampeonato();
        if ( campeonato == null ) {
            return null;
        }
        Long id = campeonato.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<PartidoDTO> partidoListToPartidoDTOList(List<Partido> list) {
        if ( list == null ) {
            return null;
        }

        List<PartidoDTO> list1 = new ArrayList<PartidoDTO>( list.size() );
        for ( Partido partido : list ) {
            list1.add( partidoMapper.toDto( partido ) );
        }

        return list1;
    }

    protected Campeonato jornadaDTOToCampeonato(JornadaDTO jornadaDTO) {
        if ( jornadaDTO == null ) {
            return null;
        }

        Campeonato campeonato = new Campeonato();

        campeonato.setId( jornadaDTO.getCampeonatoId() );

        return campeonato;
    }

    protected List<Partido> partidoDTOListToPartidoList(List<PartidoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Partido> list1 = new ArrayList<Partido>( list.size() );
        for ( PartidoDTO partidoDTO : list ) {
            list1.add( partidoMapper.toEntity( partidoDTO ) );
        }

        return list1;
    }
}
