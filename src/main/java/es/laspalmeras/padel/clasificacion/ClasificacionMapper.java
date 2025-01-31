package es.laspalmeras.padel.clasificacion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import es.laspalmeras.padel.campeonato.Campeonato;
import es.laspalmeras.padel.jugador.Jugador;

@Mapper(componentModel = "spring")
public interface ClasificacionMapper {
    ClasificacionMapper INSTANCE = Mappers.getMapper(ClasificacionMapper.class);

    @Mapping(source = "campeonato.id", target = "campeonatoId", defaultExpression = "java(null)")
    @Mapping(source = "jugador.id", target = "jugadorId", defaultExpression = "java(null)")
    ClasificacionDTO toDto(Clasificacion clasificacion);

    @Mapping(source = "campeonatoId", target = "campeonato", qualifiedByName = "fromIdToCampeonato")
    @Mapping(source = "jugadorId", target = "jugador", qualifiedByName = "fromIdToJugador")
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