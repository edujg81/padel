package es.laspalmeras.padel.campeonato;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.campeonato.model.Campeonato;
import es.laspalmeras.padel.campeonato.model.dto.CampeonatoDTO;

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
