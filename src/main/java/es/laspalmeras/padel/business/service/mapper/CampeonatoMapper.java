package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;

@Mapper(componentModel = "spring", uses = { JornadaMapper.class })
public interface CampeonatoMapper {
    
	CampeonatoMapper INSTANCE = Mappers.getMapper(CampeonatoMapper.class);
	
    //@Mapping(target = "jornadas", ignore = true) // Para evitar la recursión infinita
    @Mapping(source = "jornadas", target = "jornadas")
    CampeonatoDTO toDto(Campeonato campeonato);
    
    //@Mapping(target = "jornadas", ignore = true) // Para evitar la recursión infinita
    @Mapping(source = "jornadas", target = "jornadas")
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);
}
