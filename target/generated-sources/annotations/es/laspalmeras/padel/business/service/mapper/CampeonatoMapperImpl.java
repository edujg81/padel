package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-29T15:08:38+0100",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class CampeonatoMapperImpl implements CampeonatoMapper {

    @Override
    public CampeonatoDTO toDto(Campeonato campeonato) {
        if ( campeonato == null ) {
            return null;
        }

        CampeonatoDTO campeonatoDTO = new CampeonatoDTO();

        campeonatoDTO.setActivo( campeonato.getActivo() );
        campeonatoDTO.setCategoria( campeonato.getCategoria() );
        campeonatoDTO.setDivision( campeonato.getDivision() );
        campeonatoDTO.setEstado( campeonato.getEstado() );
        campeonatoDTO.setId( campeonato.getId() );
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

        campeonato.setActivo( campeonatoDTO.getActivo() );
        campeonato.setCategoria( campeonatoDTO.getCategoria() );
        campeonato.setDivision( campeonatoDTO.getDivision() );
        campeonato.setEstado( campeonatoDTO.getEstado() );
        campeonato.setId( campeonatoDTO.getId() );
        campeonato.setPuntosPorDerrota( campeonatoDTO.getPuntosPorDerrota() );
        campeonato.setPuntosPorVictoria( campeonatoDTO.getPuntosPorVictoria() );
        campeonato.setYear( campeonatoDTO.getYear() );

        return campeonato;
    }
}
