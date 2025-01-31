package es.laspalmeras.padel.partido.model.dto;

import java.time.LocalDate;
import java.util.List;

import es.laspalmeras.padel.ausencia.model.dto.AusenciaDTO;
import lombok.Data;

@Data
public class PartidoDTO {
    private Long id;
    private LocalDate fecha;
    private String pista;
    private String resultado;
    private String equipoGanador;
    private Boolean registrado;
    private Long jornadaId; // ID de la jornada a la que pertenece el partido
    private Long equipo1Jugador1Id; // ID del jugador 1 del equipo 1
    private Long equipo1Jugador2Id; // ID del jugador 2 del equipo 1
    private Long equipo2Jugador1Id; // ID del jugador 1 del equipo 2
    private Long equipo2Jugador2Id; // ID del jugador 2 del equipo 2
    private List<AusenciaDTO> ausencias;

    /* Juegos ganados por equipos en cada set */
    private Integer juegosGanadosEquipo1Set1;
    private Integer juegosGanadosEquipo2Set1;
    private Integer juegosGanadosEquipo1Set2;
    private Integer juegosGanadosEquipo2Set2;
    private Integer juegosGanadosEquipo1Set3;
    private Integer juegosGanadosEquipo2Set3;
    private Integer setsGanadosEquipo1;
    private Integer setsGanadosEquipo2;
}