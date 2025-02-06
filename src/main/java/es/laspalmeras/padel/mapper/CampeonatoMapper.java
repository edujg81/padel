package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.dto.CampeonatoDTO;
import es.laspalmeras.padel.enums.EstadoCampeonato;
import es.laspalmeras.padel.model.Campeonato;

@Mapper(componentModel = "spring")
public interface CampeonatoMapper {
    
	CampeonatoMapper INSTANCE = Mappers.getMapper(CampeonatoMapper.class);
	
	@Mappings({
		@Mapping(source = "jornadas", target = "jornadaIds", ignore = true),
	    @Mapping(source = "inscripciones", target = "inscripcionIds", ignore = true),
		// Convertir el enum a String
        @Mapping(source = "estado", target = "estado", qualifiedByName = "estadoToString")
    })
    CampeonatoDTO toDto(Campeonato campeonato);
    
	@Mappings({
        @Mapping(target = "inscripciones", ignore = true),
        @Mapping(target = "jornadas", ignore = true),
        // Convertir el String a enum
        @Mapping(source = "estado", target = "estado", qualifiedByName = "stringToEstado")
    })
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);
	
	@Named("estadoToString")
    default String estadoToString(EstadoCampeonato estado) {
        return estado != null ? estado.name() : null;
    }
    
    @Named("stringToEstado")
    default EstadoCampeonato stringToEstado(String estado) {
        return estado != null ? EstadoCampeonato.valueOf(estado) : null;
    }
}
