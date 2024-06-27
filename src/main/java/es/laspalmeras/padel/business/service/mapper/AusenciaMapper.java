package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.model.Ausencia;

@Mapper(componentModel = "spring")
public interface AusenciaMapper {
    AusenciaMapper INSTANCE = Mappers.getMapper(AusenciaMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "partido", target = "partido")
    @Mapping(source = "ausente", target = "ausente")
    @Mapping(source = "sustituto", target = "sustituto")
    AusenciaDTO toDto(Ausencia ausencia);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "partido", target = "partido")
    @Mapping(source = "ausente", target = "ausente")
    @Mapping(source = "sustituto", target = "sustituto")
    Ausencia toEntity(AusenciaDTO ausenciaDTO);
}