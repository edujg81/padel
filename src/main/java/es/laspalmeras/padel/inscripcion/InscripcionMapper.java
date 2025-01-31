package es.laspalmeras.padel.inscripcion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.inscripcion.model.Inscripcion;
import es.laspalmeras.padel.inscripcion.model.dto.InscripcionDTO;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {
    InscripcionMapper INSTANCE = Mappers.getMapper(InscripcionMapper.class);

    @Mapping(source = "campeonato.id", target = "campeonatoId")
    @Mapping(source = "jugador.id", target = "jugadorId")
    InscripcionDTO toDto(Inscripcion inscripcion);

    @Mapping(source = "campeonatoId", target = "campeonato.id")
    @Mapping(source = "jugadorId", target = "jugador.id")
    Inscripcion toEntity(InscripcionDTO inscripcionDTO);
}