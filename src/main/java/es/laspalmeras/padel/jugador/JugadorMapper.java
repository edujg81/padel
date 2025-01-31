package es.laspalmeras.padel.jugador;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.jugador.model.Jugador;
import es.laspalmeras.padel.jugador.model.dto.JugadorDTO;

@Mapper(componentModel = "spring")
public interface JugadorMapper {
    JugadorMapper INSTANCE = Mappers.getMapper(JugadorMapper.class);

//    @Mapping(source = "dni", target = "dni")
//    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
//    @Mapping(source = "telefono", target = "telefono")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "sexo", target = "sexo")
//    @Mapping(source = "estado", target = "estado")
//    @Mapping(source = "lesionado", target = "lesionado")
//    @Mapping(source = "fechaAlta", target = "fechaAlta")
//    @Mapping(source = "fechaBaja", target = "fechaBaja")
    JugadorDTO toDto(Jugador jugador);

//    @Mapping(source = "dni", target = "dni")
//    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
//    @Mapping(source = "telefono", target = "telefono")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "sexo", target = "sexo")
//    @Mapping(source = "estado", target = "estado")
//    @Mapping(source = "lesionado", target = "lesionado")
//    @Mapping(source = "fechaAlta", target = "fechaAlta")
//    @Mapping(source = "fechaBaja", target = "fechaBaja")
    Jugador toEntity(JugadorDTO jugadorDTO);
}
