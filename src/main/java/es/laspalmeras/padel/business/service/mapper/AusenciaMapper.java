package es.laspalmeras.padel.business.service.mapper;

import es.laspalmeras.padel.business.service.dto.AusenciaDTO;
import es.laspalmeras.padel.business.service.model.Ausencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
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
