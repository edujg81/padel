package es.laspalmeras.padel.clasificacion.model.dto;

import lombok.Data;

/**
 * DTO de tabla Clasificacion.
 *
 */
@Data
public class ClasificacionDTO {
    private Long id;
    private Long campeonatoId;
    private Long jugadorId;
    private int posicion;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int setsGanados;
    private int setsPerdidos;
    private int juegosGanados;
    private int juegosPerdidos;
}