package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.laspalmeras.padel.dto.PartidoDTO;
import es.laspalmeras.padel.model.Partido;

@Mapper(componentModel = "spring")
public interface PartidoMapper {
    //PartidoMapper INSTANCE = Mappers.getMapper(PartidoMapper.class);

//    @Mapping(source = "jornada.id", target = "jornadaId")
//    @Mapping(source = "equipo1Jugador1.id", target = "equipo1Jugador1Id")
//    @Mapping(source = "equipo1Jugador2.id", target = "equipo1Jugador2Id")
//    @Mapping(source = "equipo2Jugador1.id", target = "equipo2Jugador1Id")
//    @Mapping(source = "equipo2Jugador2.id", target = "equipo2Jugador2Id")
//    @Mapping(target = "ausencias", ignore = true)
    @Mapping(expression = "java(partido.getJornada() != null ? partido.getJornada().getId() : null)", target = "jornadaId")
    @Mapping(expression = "java(partido.getEquipo1Jugador1() != null ? partido.getEquipo1Jugador1().getId() : null)", target = "equipo1Jugador1Id")
    @Mapping(expression = "java(partido.getEquipo1Jugador2() != null ? partido.getEquipo1Jugador2().getId() : null)", target = "equipo1Jugador2Id")
    @Mapping(expression = "java(partido.getEquipo2Jugador1() != null ? partido.getEquipo2Jugador1().getId() : null)", target = "equipo2Jugador1Id")
    @Mapping(expression = "java(partido.getEquipo2Jugador2() != null ? partido.getEquipo2Jugador2().getId() : null)", target = "equipo2Jugador2Id")
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