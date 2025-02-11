package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.laspalmeras.padel.dto.ClasificacionDTO;
import es.laspalmeras.padel.model.Campeonato;
import es.laspalmeras.padel.model.Clasificacion;
import es.laspalmeras.padel.model.Jugador;

@Mapper(componentModel = "spring")
public interface ClasificacionMapper {
    @Mapping(expression = "java(clasificacion.getCampeonato() != null ? clasificacion.getCampeonato().getId() : null)", target = "campeonatoId")
    @Mapping(expression = "java(clasificacion.getJugador() != null ? clasificacion.getJugador().getId() : null)", target = "jugadorId")
    ClasificacionDTO toDto(Clasificacion clasificacion);

    @Mapping(target = "campeonato", source = "campeonatoId", qualifiedByName = "fromIdToCampeonato")
    @Mapping(target = "jugador", source = "jugadorId", qualifiedByName = "fromIdToJugador")
    Clasificacion toEntity(ClasificacionDTO clasificacionDTO);
    
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