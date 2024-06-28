package es.laspalmeras.padel.business.service.dto;

import lombok.Data;

@Data
public class CampeonatoDTO {
    private Long id;
    private Integer year;
    private String categoria;
    private Integer division;
    private String estado;
    private Boolean activo;
    private Integer puntosPorVictoria;
    private Integer puntosPorDerrota;
}