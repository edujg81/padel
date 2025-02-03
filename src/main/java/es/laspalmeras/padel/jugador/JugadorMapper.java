package es.laspalmeras.padel.jugador;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.jugador.model.Jugador;
import es.laspalmeras.padel.jugador.model.dto.JugadorDTO;

@Mapper(componentModel = "spring")
public interface JugadorMapper {
    JugadorMapper INSTANCE = Mappers.getMapper(JugadorMapper.class);

    JugadorDTO toDto(Jugador jugador);

    Jugador toEntity(JugadorDTO jugadorDTO);
}
