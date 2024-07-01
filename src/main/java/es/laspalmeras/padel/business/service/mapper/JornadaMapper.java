package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.model.Jornada;

@Mapper(componentModel = "spring", uses = {PartidoMapper.class})
public interface JornadaMapper {
    JornadaMapper INSTANCE = Mappers.getMapper(JornadaMapper.class);

    @Mapping(source = "campeonato.id", target = "campeonatoId")
    JornadaDTO toDto(Jornada jornada);

    @Mapping(source = "campeonatoId", target = "campeonato.id")
    Jornada toEntity(JornadaDTO jornadaDTO);
}