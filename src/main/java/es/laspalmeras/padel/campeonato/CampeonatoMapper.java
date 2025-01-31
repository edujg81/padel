package es.laspalmeras.padel.campeonato;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CampeonatoMapper {
    
	CampeonatoMapper INSTANCE = Mappers.getMapper(CampeonatoMapper.class);
	
	@Mappings({
////		@Mapping(source = "id", target = "id"),
////		@Mapping(source = "year", target = "year"),
////        @Mapping(source = "categoria", target = "categoria"),
////        @Mapping(source = "division", target = "division"),
////        @Mapping(source = "estado", target = "estado"),
////        @Mapping(source = "activo", target = "activo"),
////        @Mapping(source = "puntosPorVictoria", target = "puntosPorVictoria"),
////        @Mapping(source = "puntosPorDerrota", target = "puntosPorDerrota"),
		@Mapping(source = "jornadas", target = "jornadaIds", ignore = true),
	    @Mapping(source = "inscripciones", target = "inscripcionIds", ignore = true)
    })
    CampeonatoDTO toDto(Campeonato campeonato);
    
	@Mappings({
////		@Mapping(source = "id", target = "id"),
////        @Mapping(source = "year", target = "year"),
////        @Mapping(source = "categoria", target = "categoria"),
////        @Mapping(source = "division", target = "division"),
////        @Mapping(source = "estado", target = "estado"),
////        @Mapping(source = "activo", target = "activo"),
////        @Mapping(source = "puntosPorVictoria", target = "puntosPorVictoria"),
////        @Mapping(source = "puntosPorDerrota", target = "puntosPorDerrota"),
        @Mapping(target = "inscripciones", ignore = true),
        @Mapping(target = "jornadas", ignore = true)
    })
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);
}
