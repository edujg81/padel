package es.laspalmeras.padel.business.service.dto;

import es.laspalmeras.padel.business.service.model.Ausencia;
import lombok.Data;

/**
 * DTO de tabla de ausencias.
 *
 */
@Data
public class AusenciaDTO {
    private Long id;
    private Long partidoId;
    private Long ausenteId;
    private Long sustitutoId;

    public static AusenciaDTO fromEntity(Ausencia ausencia) {
        AusenciaDTO dto = new AusenciaDTO();
        dto.setId(ausencia.getId());
        dto.setPartidoId(ausencia.getPartido().getId());
        dto.setAusenteId(ausencia.getAusente().getId());
        dto.setSustitutoId(ausencia.getSustituto().getId());
        return dto;
    }

    public Ausencia toEntity() {
        Ausencia ausencia = new Ausencia();
        ausencia.setId(id);
        ausencia.getPartido().setId(partidoId);
        ausencia.getAusente().setId(ausenteId);
        ausencia.getSustituto().setId(sustitutoId);
        return ausencia;
    }
}
