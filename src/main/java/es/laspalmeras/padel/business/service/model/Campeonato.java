package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of={"año", "categoria", "division"})
@ToString
@Entity
@Table(name="CAMPEONATO")
public class Campeonato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer año;
    
    @Pattern(regexp = "Masculino|Femenino|Mixto", message = "Categoría debe ser 'Masculino', 'Femenino' o 'Mixto'")
    private String categoria; // "Masculino", "Femenino", "Mixto"
    
    private Integer division; // 1, 2, 3...
    
    @Pattern(regexp = "Sin iniciar|En curso|Finalizado", message = "Estado debe ser 'Sin iniciar', 'En curso' o 'Finalizado'")
    private String estado; // "Sin iniciar", "En curso", "Finalizado"
    
    private Boolean activo;
    private Integer puntosPorVictoria = 2; // Valor predeterminado
    private Integer puntosPorDerrota = 0;  // Valor predeterminado
}