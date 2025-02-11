package es.laspalmeras.padel.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.laspalmeras.padel.dto.JornadaDTO;
import es.laspalmeras.padel.dto.PartidoDTO;
import es.laspalmeras.padel.model.Jornada;
import es.laspalmeras.padel.model.Partido;

@Mapper(componentModel = "spring", uses = {JugadorMapper.class, PartidoMapper.class})
public interface JornadaMapper {
    //JornadaMapper INSTANCE = Mappers.getMapper(JornadaMapper.class);

    @Mapping(source = "campeonato.id", target = "campeonatoId")
    JornadaDTO toDto(Jornada jornada);

    @Mapping(source = "campeonatoId", target = "campeonato.id")
    Jornada toEntity(JornadaDTO jornadaDTO);
    
    // Mapeo adicional para propiedades anidadas
    List<PartidoDTO> toPartidoDTOs(List<Partido> partidos);
    List<Partido> toPartidos(List<PartidoDTO> partidoDTOs);
}