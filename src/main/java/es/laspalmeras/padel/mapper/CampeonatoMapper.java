package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import es.laspalmeras.padel.dto.CampeonatoDTO;
import es.laspalmeras.padel.enums.EstadoCampeonato;
import es.laspalmeras.padel.model.Campeonato;

@Mapper(componentModel = "spring")
public interface CampeonatoMapper {
    
	@Mappings({
	        @Mapping(target = "jornadaIds", ignore = true),
	        @Mapping(target = "inscripcionIds", ignore = true),
	        @Mapping(source = "estado", target = "estado", qualifiedByName = "estadoToString")
	})
    CampeonatoDTO toDto(Campeonato campeonato);
    
	@Mappings({
        @Mapping(target = "jornadas", ignore = true),
        @Mapping(target = "inscripciones", ignore = true),
        @Mapping(source = "estado", target = "estado", qualifiedByName = "stringToEstado")
    })
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);
	
	@Named("estadoToString")
    default String estadoToString(EstadoCampeonato estado) {
        return estado != null ? estado.name() : null;
    }
    
    @Named("stringToEstado")
    default EstadoCampeonato stringToEstado(String estado) {
        //return estado != null ? EstadoCampeonato.valueOf(estado.toUpperCase()) : null;
        if (estado == null) return null;
        // Eliminar espacios iniciales y finales, convertir a mayúsculas y reemplazar espacios con guiones bajos
        String normalized = estado.trim().toUpperCase().replace(" ", "_");
        return EstadoCampeonato.valueOf(normalized);
    }
}
