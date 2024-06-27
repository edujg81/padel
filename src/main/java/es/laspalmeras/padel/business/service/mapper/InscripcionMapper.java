package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.InscripcionDTO;
import es.laspalmeras.padel.business.service.model.Inscripcion;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {
	InscripcionMapper INSTANCE = Mappers.getMapper(InscripcionMapper.class);
	
	InscripcionDTO toDto(Inscripcion inscripcion);
    
	@Mapping(source = "id", target = "id")
	@Mapping(source = "campeonato", target = "campeonato")
	@Mapping(source = "jugador", target = "jugador")
	Inscripcion toEntity(InscripcionDTO inscripcionDTO);
}