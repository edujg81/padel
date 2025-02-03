package es.laspalmeras.padel.campeonato.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.laspalmeras.padel.inscripcion.model.Inscripcion;
import es.laspalmeras.padel.jornada.model.Jornada;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
//import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
//import lombok.NoArgsConstructor;
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
    
    @Pattern(regexp = "Sin iniciar|En curso|Finalizado", message = "Estado debe ser 'Sin iniciar', 'En curso' o 'Finalizado'")
    private String estado = "Sin iniciar"; // "Sin iniciar", "En curso", "Finalizado"
    
    private Boolean activo = true;
    private Integer puntosPorVictoria = 2; // Valor predeterminado
    private Integer puntosPorDerrota = 0;  // Valor predeterminado
    
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnoreProperties("campeonato")
    private List<Jornada> jornadas;
    
    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;
    
    @CreatedDate
    private LocalDate fechaCreacion;
    
    @LastModifiedDate
    private LocalDate fechaModificacion;
    
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
}
