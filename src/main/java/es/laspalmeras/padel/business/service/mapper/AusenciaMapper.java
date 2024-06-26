package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.model.Ausencia;

@Mapper
public interface AusenciaMapper {
    AusenciaMapper INSTANCE = Mappers.getMapper(AusenciaMapper.class);

    AusenciaDTO toDto(Ausencia ausencia);
    Ausencia toEntity(AusenciaDTO ausenciaDTO);
}