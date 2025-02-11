package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.laspalmeras.padel.dto.InscripcionDTO;
import es.laspalmeras.padel.model.Campeonato;
import es.laspalmeras.padel.model.Inscripcion;
import es.laspalmeras.padel.model.Jugador;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {
    //InscripcionMapper INSTANCE = Mappers.getMapper(InscripcionMapper.class);

    //@Mapping(source = "campeonato.id", target = "campeonatoId")
    //@Mapping(source = "jugador.id", target = "jugadorId")
    @Mapping(expression = "java(inscripcion.getCampeonato() != null ? inscripcion.getCampeonato().getId() : null)", target = "campeonatoId")
    @Mapping(expression = "java(inscripcion.getJugador() != null ? inscripcion.getJugador().getId() : null)", target = "jugadorId")
    InscripcionDTO toDto(Inscripcion inscripcion);

    //@Mapping(source = "campeonatoId", target = "campeonato.id")
    //@Mapping(source = "jugadorId", target = "jugador.id")
    @Mapping(target = "campeonato", source = "campeonatoId", qualifiedByName = "fromIdToCampeonato")
    @Mapping(target = "jugador", source = "jugadorId", qualifiedByName = "fromIdToJugador")
    Inscripcion toEntity(InscripcionDTO inscripcionDTO);
    
    @Named("fromIdToCampeonato")
    public static Campeonato fromIdToCampeonato(Long id) {
        if (id == null) return null;
        Campeonato campeonato = new Campeonato();
        campeonato.setId(id);
        return campeonato;
    }
    
    @Named("fromIdToJugador")
    public static Jugador fromIdToJugador(Long id) {
        if (id == null) return null;
        Jugador jugador = new Jugador();
        jugador.setId(id);
        return jugador;
    }
}