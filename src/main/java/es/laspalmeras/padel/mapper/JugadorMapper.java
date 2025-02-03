package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.dto.JugadorDTO;
import es.laspalmeras.padel.model.Jugador;

@Mapper(componentModel = "spring")
public interface JugadorMapper {
    JugadorMapper INSTANCE = Mappers.getMapper(JugadorMapper.class);

    JugadorDTO toDto(Jugador jugador);

    Jugador toEntity(JugadorDTO jugadorDTO);
}
