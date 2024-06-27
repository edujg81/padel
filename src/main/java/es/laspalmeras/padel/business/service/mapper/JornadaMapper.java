package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.model.Jornada;

@Mapper(componentModel = "spring", uses = {PartidoMapper.class})
public interface JornadaMapper {
	//@Mapping(target = "campeonato.jornadas", ignore = true)  // Evita la recursión infinita
	//@Mapping(source = "campeonato.id", target = "campeonato")
    JornadaDTO toDto(Jornada jornada);

    @Mapping(target = "campeonato.jornadas", ignore = true)  // Evita la recursión infinita
    @Mapping(source = "campeonato.id", target = "campeonato.id")
    Jornada toEntity(JornadaDTO jornadaDTO);
}
