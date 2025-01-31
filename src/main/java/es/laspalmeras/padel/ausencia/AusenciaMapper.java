package es.laspalmeras.padel.ausencia;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.ausencia.model.Ausencia;
import es.laspalmeras.padel.ausencia.model.dto.AusenciaDTO;

@Mapper(componentModel = "spring")
public interface AusenciaMapper {
    AusenciaMapper INSTANCE = Mappers.getMapper(AusenciaMapper.class);

    @Mapping(source = "partidoId", target = "partido.id")
    @Mapping(source = "ausenteId", target = "ausente.id")
    @Mapping(source = "sustitutoId", target = "sustituto.id")
    Ausencia toEntity(AusenciaDTO dto);

    @Mapping(source = "partido.id", target = "partidoId")
    @Mapping(source = "ausente.id", target = "ausenteId")
    @Mapping(source = "sustituto.id", target = "sustitutoId")
    AusenciaDTO toDto(Ausencia entity);
}
