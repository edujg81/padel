package es.laspalmeras.padel.jornada;

import java.time.LocalDate;
import java.util.List;

import es.laspalmeras.padel.partido.PartidoDTO;
import lombok.Data;

@Data
public class JornadaDTO {
    private Long id;
    private Integer numero;
    private LocalDate fechaInicio;
    private Long campeonatoId;
    private List<PartidoDTO> partidos;
}