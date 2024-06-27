package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.model.Ausencia;
import es.laspalmeras.padel.business.service.model.Partido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T15:22:27+0200",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class PartidoMapperImpl implements PartidoMapper {

    @Autowired
    private JugadorMapper jugadorMapper;
    @Autowired
    private JornadaMapper jornadaMapper;
    @Autowired
    private AusenciaMapper ausenciaMapper;

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
        partidoDTO.setJornada( jornadaMapper.toDto( partido.getJornada() ) );
        partidoDTO.setEquipo1Jugador1( jugadorMapper.toDto( partido.getEquipo1Jugador1() ) );
        partidoDTO.setEquipo1Jugador2( jugadorMapper.toDto( partido.getEquipo1Jugador2() ) );
        partidoDTO.setEquipo2Jugador1( jugadorMapper.toDto( partido.getEquipo2Jugador1() ) );
        partidoDTO.setEquipo2Jugador2( jugadorMapper.toDto( partido.getEquipo2Jugador2() ) );
        partidoDTO.setAusencias( ausenciaListToAusenciaDTOList( partido.getAusencias() ) );
        partidoDTO.setJuegosGanadosEquipo1Set1( partido.getJuegosGanadosEquipo1Set1() );
        partidoDTO.setJuegosGanadosEquipo1Set2( partido.getJuegosGanadosEquipo1Set2() );
        partidoDTO.setJuegosGanadosEquipo1Set3( partido.getJuegosGanadosEquipo1Set3() );
        partidoDTO.setJuegosGanadosEquipo2Set1( partido.getJuegosGanadosEquipo2Set1() );
        partidoDTO.setJuegosGanadosEquipo2Set2( partido.getJuegosGanadosEquipo2Set2() );
        partidoDTO.setJuegosGanadosEquipo2Set3( partido.getJuegosGanadosEquipo2Set3() );
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

        partido.setId( partidoDTO.getId() );
        partido.setFecha( partidoDTO.getFecha() );
        partido.setPista( partidoDTO.getPista() );
        partido.setResultado( partidoDTO.getResultado() );
        partido.setEquipoGanador( partidoDTO.getEquipoGanador() );
        partido.setRegistrado( partidoDTO.getRegistrado() );
        partido.setJornada( jornadaMapper.toEntity( partidoDTO.getJornada() ) );
        partido.setEquipo1Jugador1( jugadorMapper.toEntity( partidoDTO.getEquipo1Jugador1() ) );
        partido.setEquipo1Jugador2( jugadorMapper.toEntity( partidoDTO.getEquipo1Jugador2() ) );
        partido.setEquipo2Jugador1( jugadorMapper.toEntity( partidoDTO.getEquipo2Jugador1() ) );
        partido.setEquipo2Jugador2( jugadorMapper.toEntity( partidoDTO.getEquipo2Jugador2() ) );
        partido.setAusencias( ausenciaDTOListToAusenciaList( partidoDTO.getAusencias() ) );
        partido.setJuegosGanadosEquipo1Set1( partidoDTO.getJuegosGanadosEquipo1Set1() );
        partido.setJuegosGanadosEquipo1Set2( partidoDTO.getJuegosGanadosEquipo1Set2() );
        partido.setJuegosGanadosEquipo1Set3( partidoDTO.getJuegosGanadosEquipo1Set3() );
        partido.setJuegosGanadosEquipo2Set1( partidoDTO.getJuegosGanadosEquipo2Set1() );
        partido.setJuegosGanadosEquipo2Set2( partidoDTO.getJuegosGanadosEquipo2Set2() );
        partido.setJuegosGanadosEquipo2Set3( partidoDTO.getJuegosGanadosEquipo2Set3() );
        partido.setSetsGanadosEquipo1( partidoDTO.getSetsGanadosEquipo1() );
        partido.setSetsGanadosEquipo2( partidoDTO.getSetsGanadosEquipo2() );

        return partido;
    }

    protected List<AusenciaDTO> ausenciaListToAusenciaDTOList(List<Ausencia> list) {
        if ( list == null ) {
            return null;
        }

        List<AusenciaDTO> list1 = new ArrayList<AusenciaDTO>( list.size() );
        for ( Ausencia ausencia : list ) {
            list1.add( ausenciaMapper.toDto( ausencia ) );
        }

        return list1;
    }

    protected List<Ausencia> ausenciaDTOListToAusenciaList(List<AusenciaDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Ausencia> list1 = new ArrayList<Ausencia>( list.size() );
        for ( AusenciaDTO ausenciaDTO : list ) {
            list1.add( ausenciaMapper.toEntity( ausenciaDTO ) );
        }

        return list1;
    }
}
