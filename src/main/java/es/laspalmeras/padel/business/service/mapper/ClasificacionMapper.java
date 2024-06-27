package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.ClasificacionDTO;
import es.laspalmeras.padel.business.service.model.Clasificacion;

@Mapper(componentModel = "spring")
public interface ClasificacionMapper {
	ClasificacionMapper INSTANCE = Mappers.getMapper(ClasificacionMapper.class);
	
	ClasificacionDTO toDto(Clasificacion clasificacion);
    
    Clasificacion toEntity(ClasificacionDTO clasificacionDTO);
}