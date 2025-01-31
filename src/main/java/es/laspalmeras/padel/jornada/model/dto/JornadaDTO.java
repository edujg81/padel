package es.laspalmeras.padel.jornada.model.dto;

import java.time.LocalDate;
import java.util.List;

import es.laspalmeras.padel.partido.model.dto.PartidoDTO;
import lombok.Data;

@Data
public class JornadaDTO {
    private Long id;
    private Integer numero;
    private LocalDate fechaInicio;
    private Long campeonatoId;
    private List<PartidoDTO> partidos;
}