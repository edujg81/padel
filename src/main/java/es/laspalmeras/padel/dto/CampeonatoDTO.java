package es.laspalmeras.padel.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * DTO de tabla de campeonatos.
 *
 */
@Data
public class CampeonatoDTO {
    private Long id;
    
    @NotNull
    private Integer year;
    
    @NotBlank
    @Pattern(regexp = "Masculino|Femenino|Mixto", message = "Categoría debe ser 'Masculino', 'Femenino' o 'Mixto'")
    private String categoria;
    
    @NotNull
    @Pattern(regexp = "\\d+", message = "División debe ser un número entero")
    private Integer division;
    
    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "SIN_INICIAR|EN_CURSO|FINALIZADO", message = "Estado debe ser 'SIN_INICIAR', 'EN_CURSO' o 'FINALIZADO'")
    private String estado = "SIN_INICIAR";
    
    private Boolean activo = true;
    private Integer puntosPorVictoria = 2;
    private Integer puntosPorDerrota = 0;
    private List<Long> jornadaIds;
    private List<Long> inscripcionIds;
}