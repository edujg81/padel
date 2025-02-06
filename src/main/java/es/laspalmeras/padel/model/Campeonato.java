package es.laspalmeras.padel.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.laspalmeras.padel.enums.EstadoCampeonato;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Representa un campeonato de padel.
 */

@Entity
@Table(name="CAMPEONATO")
@Getter
@Setter
@EqualsAndHashCode(of={"year", "categoria", "division"})
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Campeonato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="anio")
    private Integer year;
    
    @NotBlank
    @Pattern(regexp = "Masculino|Femenino|Mixto", message = "Categoría debe ser 'Masculino', 'Femenino' o 'Mixto'")
    private String categoria; // "Masculino", "Femenino", "Mixto"
    
    @NotNull
    @Pattern(regexp = "\\d+", message = "División debe ser un número entero")
    private Integer division; // 1, 2, 3...
    
    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCampeonato estado = EstadoCampeonato.SIN_INICIAR;
    
    private Boolean activo = true;
    private Integer puntosPorVictoria = 2; // Valor predeterminado
    private Integer puntosPorDerrota = 0;  // Valor predeterminado
    
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnoreProperties("campeonato")
    private List<Jornada> jornadas;
    
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;
    
}
