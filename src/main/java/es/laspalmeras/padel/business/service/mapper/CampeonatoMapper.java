package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.CampeonatoDTO;
import es.laspalmeras.padel.business.service.model.Campeonato;

@Mapper(componentModel = "spring")
public interface CampeonatoMapper {
    
	CampeonatoMapper INSTANCE = Mappers.getMapper(CampeonatoMapper.class);
	
	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "year", target = "year"),
        @Mapping(source = "categoria", target = "categoria"),
        @Mapping(source = "division", target = "division"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "activo", target = "activo"),
        @Mapping(source = "puntosPorVictoria", target = "puntosPorVictoria"),
        @Mapping(source = "puntosPorDerrota", target = "puntosPorDerrota")
    })
    CampeonatoDTO toDto(Campeonato campeonato);
    
	@Mappings({
		@Mapping(source = "id", target = "id"),
        @Mapping(source = "year", target = "year"),
        @Mapping(source = "categoria", target = "categoria"),
        @Mapping(source = "division", target = "division"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "activo", target = "activo"),
        @Mapping(source = "puntosPorVictoria", target = "puntosPorVictoria"),
        @Mapping(source = "puntosPorDerrota", target = "puntosPorDerrota"),
        @Mapping(target = "inscripciones", ignore = true),
        @Mapping(target = "jornadas", ignore = true)
    })
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);
}
