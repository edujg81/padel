package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.dto.CampeonatoDTO;
import es.laspalmeras.padel.model.Campeonato;

@Mapper(componentModel = "spring")
public interface CampeonatoMapper {
    
	CampeonatoMapper INSTANCE = Mappers.getMapper(CampeonatoMapper.class);
	
	@Mappings({
		@Mapping(source = "jornadas", target = "jornadaIds", ignore = true),
	    @Mapping(source = "inscripciones", target = "inscripcionIds", ignore = true)
    })
    CampeonatoDTO toDto(Campeonato campeonato);
    
	@Mappings({
        @Mapping(target = "inscripciones", ignore = true),
        @Mapping(target = "jornadas", ignore = true)
    })
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);
}
