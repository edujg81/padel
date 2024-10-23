package es.laspalmeras.padel.business.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * DTO de tabla de campeonatos.
 *
 */
@Data
public class CampeonatoDTO {
    private Long id;
    
    @NotBlank
    private Integer year;
    
    @NotBlank
    @Pattern(regexp = "Masculino|Femenino|Mixto", message = "Categoría debe ser 'Masculino', 'Femenino' o 'Mixto'")
    private String categoria;
    
    @NotBlank
    @Pattern(regexp = "\\d+", message = "División debe ser un número entero")
    private Integer division;
    
    @Pattern(regexp = "Sin iniciar|En curso|Finalizado", message = "Estado debe ser 'Sin iniciar', 'En curso' o 'Finalizado'")
    private String estado = "Sin iniciar";
    
    private Boolean activo = true;
    private Integer puntosPorVictoria = 2;
    private Integer puntosPorDerrota = 0;
}