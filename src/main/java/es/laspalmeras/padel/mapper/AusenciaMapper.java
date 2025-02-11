package es.laspalmeras.padel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.laspalmeras.padel.dto.AusenciaDTO;
import es.laspalmeras.padel.model.Ausencia;
import es.laspalmeras.padel.model.Jugador;
import es.laspalmeras.padel.model.Partido;

@Mapper(componentModel = "spring")
public interface AusenciaMapper {

    @Mapping(source = "partidoId", target = "partido", qualifiedByName = "fromIdToPartido")
    @Mapping(source = "ausenteId", target = "ausente", qualifiedByName = "fromIdToJugador")
    @Mapping(source = "sustitutoId", target = "sustituto", qualifiedByName = "fromIdToJugador")
    Ausencia toEntity(AusenciaDTO dto);

    @Mapping(expression = "java(entity.getPartido() != null ? entity.getPartido().getId() : null)", target = "partidoId")
    @Mapping(expression = "java(entity.getAusente() != null ? entity.getAusente().getId() : null)", target = "ausenteId")
    @Mapping(expression = "java(entity.getSustituto() != null ? entity.getSustituto().getId() : null)", target = "sustitutoId")
    AusenciaDTO toDto(Ausencia entity);
    
    @Named("fromIdToPartido")
    public static Partido fromIdToPartido(Long id) {
        if (id == null) return null;
        Partido partido = new Partido();
        partido.setId(id);
        return partido;
    }

    @Named("fromIdToJugador")
    public static Jugador fromIdToJugador(Long id) {
        if (id == null) return null;
        Jugador jugador = new Jugador();
        jugador.setId(id);
        return jugador;
    }
}
