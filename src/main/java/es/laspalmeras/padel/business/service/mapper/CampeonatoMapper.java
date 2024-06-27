package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;

@Mapper(componentModel = "spring", uses = { JornadaMapper.class })
public interface CampeonatoMapper {
    
    //@Mapping(target = "jornadas", ignore = true) // Para evitar la recursión infinita
    CampeonatoDTO toDto(Campeonato campeonato);
    
    @Mapping(target = "jornadas", ignore = true) // Para evitar la recursión infinita
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);
}
