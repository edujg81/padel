package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of={"year", "categoria", "division"})
@ToString
@Data
@NoArgsConstructor
@Entity
@Table(name="CAMPEONATO")
public class Campeonato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="anio")
    private Integer year;
    
    @NotBlank
    @Pattern(regexp = "Masculino|Femenino|Mixto", message = "Categoría debe ser 'Masculino', 'Femenino' o 'Mixto'")
    private String categoria; // "Masculino", "Femenino", "Mixto"
    
    @NotBlank
    @Pattern(regexp = "?", message = "División debe ser un número entero")
    private Integer division; // 1, 2, 3...
    
    @Pattern(regexp = "Sin iniciar|En curso|Finalizado", message = "Estado debe ser 'Sin iniciar', 'En curso' o 'Finalizado'")
    private String estado = "Sin iniciar"; // "Sin iniciar", "En curso", "Finalizado"
    
    private Boolean activo = true;
    private Integer puntosPorVictoria = 2; // Valor predeterminado
    private Integer puntosPorDerrota = 0;  // Valor predeterminado
    
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jornada> jornadas;
    
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscripciones;
}