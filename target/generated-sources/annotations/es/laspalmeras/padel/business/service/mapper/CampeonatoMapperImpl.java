package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-30T13:14:58+0100",
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

        campeonatoDTO.setId( campeonato.getId() );
        campeonatoDTO.setYear( campeonato.getYear() );
        campeonatoDTO.setCategoria( campeonato.getCategoria() );
        campeonatoDTO.setDivision( campeonato.getDivision() );
        campeonatoDTO.setEstado( campeonato.getEstado() );
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

        campeonato.setId( campeonatoDTO.getId() );
        campeonato.setYear( campeonatoDTO.getYear() );
        campeonato.setCategoria( campeonatoDTO.getCategoria() );
        campeonato.setDivision( campeonatoDTO.getDivision() );
        campeonato.setEstado( campeonatoDTO.getEstado() );
        campeonato.setActivo( campeonatoDTO.getActivo() );
        campeonato.setPuntosPorVictoria( campeonatoDTO.getPuntosPorVictoria() );
        campeonato.setPuntosPorDerrota( campeonatoDTO.getPuntosPorDerrota() );

        return campeonato;
    }
}
