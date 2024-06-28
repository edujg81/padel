package es.laspalmeras.padel.business.service.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class JornadaDTO {
    private Long id;
    private Integer numero;
    private LocalDate fechaInicio;
    private Long campeonatoId;
    private List<PartidoDTO> partidos;
}