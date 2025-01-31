package es.laspalmeras.padel.jornada;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.jornada.model.Jornada;
import es.laspalmeras.padel.jornada.model.dto.JornadaDTO;
import es.laspalmeras.padel.jugador.JugadorMapper;
import es.laspalmeras.padel.partido.PartidoMapper;
import es.laspalmeras.padel.partido.model.Partido;
import es.laspalmeras.padel.partido.model.dto.PartidoDTO;

@Mapper(componentModel = "spring", uses = {JugadorMapper.class, PartidoMapper.class})
public interface JornadaMapper {
    JornadaMapper INSTANCE = Mappers.getMapper(JornadaMapper.class);

    @Mapping(source = "campeonato.id", target = "campeonatoId")
    JornadaDTO toDto(Jornada jornada);

    @Mapping(source = "campeonatoId", target = "campeonato.id")
    Jornada toEntity(JornadaDTO jornadaDTO);
    
    // Mapeo adicional para propiedades anidadas
    List<PartidoDTO> toPartidoDTOs(List<Partido> partidos);
    List<Partido> toPartidos(List<PartidoDTO> partidoDTOs);
}