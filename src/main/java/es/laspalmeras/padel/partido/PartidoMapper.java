package es.laspalmeras.padel.partido;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartidoMapper {
    PartidoMapper INSTANCE = Mappers.getMapper(PartidoMapper.class);

    @Mapping(source = "jornada.id", target = "jornadaId")
    @Mapping(source = "equipo1Jugador1.id", target = "equipo1Jugador1Id")
    @Mapping(source = "equipo1Jugador2.id", target = "equipo1Jugador2Id")
    @Mapping(source = "equipo2Jugador1.id", target = "equipo2Jugador1Id")
    @Mapping(source = "equipo2Jugador2.id", target = "equipo2Jugador2Id")
    @Mapping(target = "ausencias", ignore = true)
    PartidoDTO toDto(Partido partido);

    @Mapping(source = "jornadaId", target = "jornada.id")
    @Mapping(source = "equipo1Jugador1Id", target = "equipo1Jugador1.id")
    @Mapping(source = "equipo1Jugador2Id", target = "equipo1Jugador2.id")
    @Mapping(source = "equipo2Jugador1Id", target = "equipo2Jugador1.id")
    @Mapping(source = "equipo2Jugador2Id", target = "equipo2Jugador2.id")
    @Mapping(target = "ausencias", ignore = true)
    Partido toEntity(PartidoDTO partidoDTO);
}