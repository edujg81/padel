package es.laspalmeras.padel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AusenciaDTO {
    private Long id;
    private Long partidoId;
    
    @NotNull(message = "El ID del jugador ausente es obligatorio")
    private Long ausenteId;
    
    @NotNull(message = "El ID del jugador sustituto es obligatorio")
    private Long sustitutoId;

//    public static AusenciaDTO fromEntity(Ausencia ausencia) {
//        AusenciaDTO dto = new AusenciaDTO();
//        dto.setId(ausencia.getId());
//        dto.setPartidoId(ausencia.getPartido().getId());
//        dto.setAusenteId(ausencia.getAusente().getId());
//        dto.setSustitutoId(ausencia.getSustituto().getId());
//        return dto;
//    }
//
//    public Ausencia toEntity() {
//        Ausencia ausencia = new Ausencia();
//        ausencia.setId(id);
//        return ausencia;
//    }
}
