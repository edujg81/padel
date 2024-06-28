package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.ClasificacionDTO;
import es.laspalmeras.padel.business.service.model.Clasificacion;

@Mapper
public interface ClasificacionMapper {
    ClasificacionMapper INSTANCE = Mappers.getMapper(ClasificacionMapper.class);

    @Mapping(source = "campeonato.id", target = "campeonatoId")
    @Mapping(source = "jugador.id", target = "jugadorId")
    ClasificacionDTO toDto(Clasificacion clasificacion);

    @Mapping(source = "campeonatoId", target = "campeonato.id")
    @Mapping(source = "jugadorId", target = "jugador.id")
    Clasificacion toEntity(ClasificacionDTO clasificacionDTO);
}