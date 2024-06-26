package es.laspalmeras.padel.business.service.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JornadaDTO {
    private Long id;
    private Integer numero;
    private LocalDate fechaInicio;
    private CampeonatoDTO campeonato;
    private List<PartidoDTO> partidos;
    
    // Getters y Setters
}
