package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.PartidoDTO;
import es.laspalmeras.padel.business.service.model.Partido;

@Mapper(componentModel = "spring")
public interface PartidoMapper {
    PartidoMapper INSTANCE = Mappers.getMapper(PartidoMapper.class);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "pista", target = "pista")
    @Mapping(source = "resultado", target = "resultado")
    @Mapping(source = "equipoGanador", target = "equipoGanador")
    @Mapping(source = "registrado", target = "registrado")
    @Mapping(source = "jornada", target = "jornada")
    @Mapping(source = "equipo1Jugador1", target = "equipo1Jugador1")
    @Mapping(source = "equipo1Jugador2", target = "equipo1Jugador2")
    @Mapping(source = "equipo2Jugador1", target = "equipo2Jugador1")
    @Mapping(source = "equipo2Jugador2", target = "equipo2Jugador2")
    @Mapping(source = "ausencias", target = "ausencias")
    @Mapping(source = "juegosGanadosEquipo1Set1", target = "juegosGanadosEquipo1Set1")
    @Mapping(source = "juegosGanadosEquipo1Set2", target = "juegosGanadosEquipo1Set2")
    @Mapping(source = "juegosGanadosEquipo1Set3", target = "juegosGanadosEquipo1Set3")
    @Mapping(source = "juegosGanadosEquipo2Set1", target = "juegosGanadosEquipo2Set1")
    @Mapping(source = "juegosGanadosEquipo2Set2", target = "juegosGanadosEquipo2Set2")
    @Mapping(source = "juegosGanadosEquipo2Set3", target = "juegosGanadosEquipo2Set3")
    @Mapping(source = "setsGanadosEquipo1", target = "setsGanadosEquipo1")
    @Mapping(source = "setsGanadosEquipo2", target = "setsGanadosEquipo2")
    PartidoDTO toDto(Partido partido);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "pista", target = "pista")
    @Mapping(source = "resultado", target = "resultado")
    @Mapping(source = "equipoGanador", target = "equipoGanador")
    @Mapping(source = "registrado", target = "registrado")
    @Mapping(source = "jornada", target = "jornada")
    @Mapping(source = "equipo1Jugador1", target = "equipo1Jugador1")
    @Mapping(source = "equipo1Jugador2", target = "equipo1Jugador2")
    @Mapping(source = "equipo2Jugador1", target = "equipo2Jugador1")
    @Mapping(source = "equipo2Jugador2", target = "equipo2Jugador2")
    @Mapping(source = "ausencias", target = "ausencias")
    @Mapping(source = "juegosGanadosEquipo1Set1", target = "juegosGanadosEquipo1Set1")
    @Mapping(source = "juegosGanadosEquipo1Set2", target = "juegosGanadosEquipo1Set2")
    @Mapping(source = "juegosGanadosEquipo1Set3", target = "juegosGanadosEquipo1Set3")
    @Mapping(source = "juegosGanadosEquipo2Set1", target = "juegosGanadosEquipo2Set1")
    @Mapping(source = "juegosGanadosEquipo2Set2", target = "juegosGanadosEquipo2Set2")
    @Mapping(source = "juegosGanadosEquipo2Set3", target = "juegosGanadosEquipo2Set3")
    @Mapping(source = "setsGanadosEquipo1", target = "setsGanadosEquipo1")
    @Mapping(source = "setsGanadosEquipo2", target = "setsGanadosEquipo2")
    Partido toEntity(PartidoDTO partidoDTO);
}