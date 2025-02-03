package es.laspalmeras.padel.inscripcion.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class InscripcionDTO {
    private Long id;
    private Long campeonatoId;
    private Long jugadorId;
    private LocalDate fechaInscripcion;
    private LocalDate fechaDesinscripcion;
}