package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.model.Partido;

@Mapper
public interface PartidoMapper {
    PartidoMapper INSTANCE = Mappers.getMapper(PartidoMapper.class);

    PartidoDTO toDto(Partido partido);
    Partido toEntity(PartidoDTO partidoDTO);
}