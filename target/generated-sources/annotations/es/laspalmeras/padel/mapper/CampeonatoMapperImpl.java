package es.laspalmeras.padel.mapper;

import es.laspalmeras.padel.dto.CampeonatoDTO;
import es.laspalmeras.padel.model.Campeonato;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T15:07:43+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class CampeonatoMapperImpl implements CampeonatoMapper {

    @Override
    public CampeonatoDTO toDto(Campeonato campeonato) {
        if ( campeonato == null ) {
            return null;
        }

        CampeonatoDTO campeonatoDTO = new CampeonatoDTO();

        campeonatoDTO.setEstado( estadoToString( campeonato.getEstado() ) );
        campeonatoDTO.setId( campeonato.getId() );
        campeonatoDTO.setYear( campeonato.getYear() );
        campeonatoDTO.setCategoria( campeonato.getCategoria() );
        campeonatoDTO.setDivision( campeonato.getDivision() );
        campeonatoDTO.setActivo( campeonato.getActivo() );
        campeonatoDTO.setPuntosPorVictoria( campeonato.getPuntosPorVictoria() );
        campeonatoDTO.setPuntosPorDerrota( campeonato.getPuntosPorDerrota() );

        return campeonatoDTO;
    }

    @Override
    public Campeonato toEntity(CampeonatoDTO campeonatoDTO) {
        if ( campeonatoDTO == null ) {
            return null;
        }

        Campeonato campeonato = new Campeonato();

        campeonato.setEstado( stringToEstado( campeonatoDTO.getEstado() ) );
        campeonato.setId( campeonatoDTO.getId() );
        campeonato.setYear( campeonatoDTO.getYear() );
        campeonato.setCategoria( campeonatoDTO.getCategoria() );
        campeonato.setDivision( campeonatoDTO.getDivision() );
        campeonato.setActivo( campeonatoDTO.getActivo() );
        campeonato.setPuntosPorVictoria( campeonatoDTO.getPuntosPorVictoria() );
        campeonato.setPuntosPorDerrota( campeonatoDTO.getPuntosPorDerrota() );

        return campeonato;
    }
}
