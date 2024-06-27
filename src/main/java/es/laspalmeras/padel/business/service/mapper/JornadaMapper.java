package es.laspalmeras.padel.business.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.model.Jornada;

@Mapper(componentModel = "spring", uses = {PartidoMapper.class})
public interface JornadaMapper {
	
	JornadaMapper INSTANCE = Mappers.getMapper(JornadaMapper.class);
	
	@Mapping(target = "campeonato.jornadas", ignore = true)  // Evita la recursión infinita
	@Mapping(source = "campeonato.id", target = "campeonato")
	@Mapping(source = "partidos", target = "partidos")
    JornadaDTO toDto(Jornada jornada);

    @Mapping(target = "campeonato.jornadas", ignore = true)  // Evita la recursión infinita
    @Mapping(source = "campeonato.id", target = "campeonato.id")
    @Mapping(source = "partidos", target = "partidos")
    Jornada toEntity(JornadaDTO jornadaDTO);
}
